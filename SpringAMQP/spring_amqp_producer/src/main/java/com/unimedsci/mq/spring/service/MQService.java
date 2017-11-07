package com.unimedsci.mq.spring.service;

import com.unimedsci.mq.common.ResponseModel;

public interface MQService {

    /**
     * 发消息
     *
     * @param exchange
     * @param routingKey
     * @param message
     * @return
     */
    ResponseModel<String> sendMessage(String exchange, String routingKey, String message);
}
