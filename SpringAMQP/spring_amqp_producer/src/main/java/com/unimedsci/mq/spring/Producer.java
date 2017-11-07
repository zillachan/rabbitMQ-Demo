//package com.unimedsci.mq.spring;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class Producer implements CommandLineRunner {
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    @Override
//    public void run(String... args) throws Exception {
//        log.info("Sending message...exchange,topic.message,Hello from RabbitMQ!");
//        rabbitTemplate.convertAndSend("unimedsci", "topic.message", "Hello from RabbitMQ!");
//        rabbitTemplate.convertAndSend("unimedsci", "topic.message2", "Hello from RabbitMQ!");
//        rabbitTemplate.convertAndSend("unimedsci.broadcaust", "", "broadcaust...");
////        context.close();
//    }
//}
