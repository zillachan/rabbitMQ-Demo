package com.unimedsci.mq.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "zilla.p2.message1")
    public void process1(String str) {
        log.info("process p2.message1:" + str);
    }

    @RabbitListener(queues = "zilla.p2.message2")
    public void process2(String str) {
        log.info("process p2.message2:" + str);
    }

    @RabbitListener(queues = "zilla.p2.messages")
    public void process(String str) {
        log.info("process p2 messages:" + str);
    }
}
