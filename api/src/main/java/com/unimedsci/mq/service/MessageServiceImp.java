package com.unimedsci.mq.service;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.unimedsci.mq.common.ResponseModel;
import com.unimedsci.mq.config.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    private MQConfig mqConfig;

    private Connection connection = null;
    private Channel channel = null;

    Map<String, Object> args = new HashMap<String, Object>();

    public MessageServiceImp() {
        args.put("x-message-ttl", 60000 * 60 * 24 * 14);//持久化14天
    }

    @Override
    public ResponseModel<String> addMessage(String exchangeName, String routingKey, String message) {
        String actuallyRoutingKey = "com.unimedsci.mq." + routingKey;
        if (channel == null) {
            init(exchangeName, actuallyRoutingKey);
        }
        try {
            channel.basicPublish(exchangeName, actuallyRoutingKey, null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseModel<>(200, e.getMessage());
        }
        log.info("接收到数据：exchangeName:" + exchangeName + " routingKey:" + actuallyRoutingKey
                + " message:" + message);
        return new ResponseModel<>();
    }

    public void init(String exchangeName, String routingKey) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(mqConfig.getHost());
            factory.setUsername("guest");
            factory.setPassword("guest");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, args);
            channel.queueBind("testqueue", exchangeName, routingKey);
            log.info("========生产者服务启动成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("========生产者服务启动失败！");
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (Exception ignore) {
//                }
//            }
        }
    }
}
