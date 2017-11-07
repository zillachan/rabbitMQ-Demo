package com.unimedsci.mq;

import com.unimedsci.mq.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 生产者App入口
 */
@SpringBootApplication
@Slf4j
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner start(RecordService service) {
        return (args -> {
            service.start("localhost", "health_record_mq", "com.unimedsci.mq.com.company.project1");
        });
    }
}
