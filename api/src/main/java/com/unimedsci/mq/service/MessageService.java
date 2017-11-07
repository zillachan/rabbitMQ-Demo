package com.unimedsci.mq.service;

import com.unimedsci.mq.common.ResponseModel;

public interface MessageService {
    /**
     * 添加一条记录
     *
     * @param exchangeName
     * @param routingKey
     * @param contents
     * @return
     */
    ResponseModel<String> addMessage(String exchangeName, String routingKey, String contents);
}
