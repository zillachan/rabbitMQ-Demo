package com.unimedsci.mq.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "zilla.p1.message1")
    public void process1(String str) {
        log.info("process p1.message1:" + str);
    }

    @RabbitListener(queues = "zilla.p1.message2")
    public void process2(String str) {
        log.info("process p1.message2:" + str);
    }

    @RabbitListener(queues = "zilla.p1.messages")
    public void process(String str) {
        log.info("process messages:" + str);
    }

//    @RabbitListener(queues = "fanout.A")
//    public void processA(String str) {
//        log.info("Receive fanout.A:" + str);
//    }
//
//    @RabbitListener(queues = "fanout.B")
//    public void processB(String str) {
//        log.info("Receive fanout.B:" + str);
//    }
//
//    @RabbitListener(queues = "fanout.C")
//    public void processC(String str) {
//        log.info("Receive fanout.C:" + str);
//    }

//    @RabbitListener(queues = "fanout.D")
//    public void processD(String str) {
//        log.info("ReceiveD:" + str);
//    }
}
