package com.unimedsci.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@ConfigurationProperties(prefix = "mq")
@Configuration
@PropertySource("classpath:registerconfig.properties")
public class RegisterConfig {
    @Value("${local.apiAddress}")
    private String localApiAddress;

    @Value("${local.exchangeName}")
    private String exchangeName;

    @Value("${local.routingKey}")
    private String routingKey;

    @Value("${local.queueName}")
    private String queueName;

    @Value("${dispatcher.register.address}")
    private String registerAddress;

//    @Value("${dispatcher.register.fanction}")
//    private String functionName;

    //注意！配置一个PropertySourcesPlaceholderConfigurer的Bean
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

    public String getLocalApiAddress() {
        return localApiAddress;
    }

    public void setLocalApiAddress(String localApiAddress) {
        this.localApiAddress = localApiAddress;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}
