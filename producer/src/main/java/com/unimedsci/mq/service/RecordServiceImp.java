package com.unimedsci.mq.service;

import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.unimedsci.mq.domain.Record;
import com.unimedsci.mq.domain.RecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImp implements RecordService {
    private static final Logger log = LoggerFactory.getLogger(RecordServiceImp.class);

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public List<Record> get100Record() {
        return recordRepository.findFirst100ByStatusIs(0);
    }

    @Override
    public List<Record> get10Record() {
        return recordRepository.findFirst100ByStatusIs(0);
    }

    @Override
    public Record getOneRecord() {
        return recordRepository.findFirstByStatusIs(0);
    }

    private Connection connection = null;
    private Channel channel = null;


    @Override
    public void start(String host, String exchangeName, String routingKey) {
        String actuallyRoutingKey = "com.unimedsci.mq." + routingKey;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            connection = factory.newConnection();
            channel = connection.createChannel();
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("x-message-ttl", 60000 * 60 * 24 * 14);//持久化14天
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, args);

            log.info("========生产者服务启动成功！");
            while (true) {
                Record record = getOneRecord();
                if (record == null) {
                    try {
                        log.info("========没有需要处理的记录，休眠5秒。。。");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    String message = new Gson().toJson(record);
                    channel.basicPublish(exchangeName, actuallyRoutingKey, null, message.getBytes("UTF-8"));
                    log.info(" [x] Sent '" + actuallyRoutingKey + "':'" + message + "'");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("========生产者服务启动失败！");
        }
    }

}
