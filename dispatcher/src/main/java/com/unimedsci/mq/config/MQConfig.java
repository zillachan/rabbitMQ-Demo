package com.unimedsci.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@ConfigurationProperties(prefix = "mq")
@Configuration
@PropertySource("classpath:mqconfig.properties")
public class MQConfig {
    @Value("${mq.host}")
    private String host;

    //注意！配置一个PropertySourcesPlaceholderConfigurer的Bean
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
