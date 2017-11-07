package com.unimedsci.mq.service;

import com.unimedsci.mq.common.ResponseModel;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 分发接口
 */
public interface APIService {

    /**
     * 提交消息体
     * 接口在dispatcher中定义
     *
     * @param apiAddress   本地接收推送的接口地址
     * @param exchangeName ExchangeName
     * @param routingKey   订阅的信息
     * @param queueName    必需参数
     * @return
     */
    @POST("register")
    @FormUrlEncoded
    Call<ResponseModel<String>> register(@Field("apiAddress") String apiAddress,
                                         @Field("exchangeName") String exchangeName,
                                         @Field("routingKey") String routingKey,
                                         @Field("queueName") String queueName);

}
