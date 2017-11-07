package com.unimedsci.mq.spring.service;

import com.unimedsci.mq.common.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQServiceImp implements MQService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public ResponseModel<String> sendMessage(String exchange, String routingKey, String message) {
        log.info("==》Sending message...exchange:" + exchange + " routingKey:" + routingKey + " message:" + message);
        //如果发送失败，以下代码会抛出异常
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        } catch (AmqpException e) {
            e.printStackTrace();
            return new ResponseModel<>(-1, e.getMessage());
        }
        log.info("========" + message + "发送成功");
        return new ResponseModel<>();
    }
}
