package com.unimedsci.mq.service;

import com.unimedsci.mq.common.ResponseModel;

public interface RegisterService {
    /**
     * 注册业务服务器接口地址，
     * <br>注意：接口地址必需以/结尾，例如http://localhost:8080/receiver/
     * <br>另外：接收端的接口地址必需是receiver.do
     * <br>完整的地址就形如：http://localhost:8080/receiver/receiver.do
     *
     * @param apiAddress
     * @param exchangeName
     * @param routingKey
     * @param queueName
     * @return
     */
    ResponseModel<String> register(String apiAddress, String exchangeName, String routingKey,String queueName);

    /**
     * 解除注册
     *
     * @param apiAddress
     * @param exchangeName
     * @param routingKey
     * @return
     */
    ResponseModel<String> unregister(String apiAddress, String exchangeName, String routingKey);


}
