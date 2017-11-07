package com.unimedsci.mq.service;

import com.rabbitmq.client.*;
import com.unimedsci.mq.common.ResponseModel;
import com.unimedsci.mq.config.MQConfig;
import com.unimedsci.mq.domain.Register;
import com.unimedsci.mq.domain.RegisterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class ConsumerServiceImp implements ConsumerService {

    @Autowired
    private MQConfig mqConfig;
    @Autowired
    RegisterRepository registerRepository;

    private ConnectionFactory factory = null;
    //    private String queueName = null;
//    private DispatchService dispatchService = null;

    @Override
    public void start() throws Exception {
        factory = new ConnectionFactory();
        factory.setHost(mqConfig.getHost());
        List<Register> registers = registerRepository.findAllByStatusNot0();
        for (Register register : registers) {
            startOnRegister(register);
        }
    }

    @Override
    public void startOnRegister(Register register) {
        try {
            Connection connection = factory.newConnection();
            Channel c = connection.createChannel();
            c.exchangeDeclare(register.getExchangeName(), BuiltinExchangeType.TOPIC, true);
            c.queueBind(register.getQueueName(), register.getExchangeName(), register.getRoutingKey());
            c.basicConsume(register.getQueueName(), false, new MyConsumer(c, register));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    class MyConsumer extends DefaultConsumer {

        private DispatchService dispatchService;

        private Register register;

        /**
         * Constructs a new instance and records its association to the passed-in channel.
         *
         * @param channel the channel to which this consumer is attached
         */
        public MyConsumer(Channel channel) {
            super(channel);
        }

        public MyConsumer(Channel channel, Register register) {
            super(channel);
            this.register = register;
            createService();
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope,
                                   AMQP.BasicProperties properties, byte[] body) throws IOException {
            String message = new String(body, "UTF-8");
            log.info(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");

            Call<ResponseModel<String>> responseModelCall = dispatchService.push(message);
            responseModelCall.enqueue(new Callback<ResponseModel<String>>() {
                @Override
                public void onResponse(Call<ResponseModel<String>> call, Response<ResponseModel<String>> response) {
                    try {
                        log.info("Push success," + "address: " + register.getAddress() + " exchange:" + register.getExchangeName() +
                                " routingKey:" + register.getRoutingKey() +
                                " message:" + message);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    } finally {
                        try {
                            getChannel().basicAck(envelope.getDeliveryTag(), false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel<String>> call, Throwable t) {
                    log.error("Push fail," + "address: " + register.getAddress() +
                            " reason:" + t.getMessage() +
                            " exchange:" + register.getExchangeName() +
                            " routingKey:" + register.getRoutingKey() +
                            " message:" + message);
                }
            });
        }

        private void createService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(register.getAddress())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            dispatchService = retrofit.create(DispatchService.class);
        }
    }
}
