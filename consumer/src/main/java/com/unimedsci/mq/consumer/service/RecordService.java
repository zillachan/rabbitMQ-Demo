package com.unimedsci.mq.consumer.service;

public interface RecordService {
    void start(String host, String exchangeName, String routingKey);
}
