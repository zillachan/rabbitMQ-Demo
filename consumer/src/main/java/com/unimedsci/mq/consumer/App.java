package com.unimedsci.mq.consumer;

import com.unimedsci.mq.consumer.service.RecordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 消费者应用入口
 */
@SpringBootApplication
//@EntityScan(basePackageClasses = Record.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner start(RecordService service) {
        return (args -> {
            service.start("localhost","health_record_mq","record");
        });
    }
}
