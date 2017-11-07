package com.unimedsci.mq.service;

import com.unimedsci.mq.common.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 分发接口
 */
public interface DispatchService {

    /**
     * 提交消息体
     *
     * @param message
     * @return
     */
    @POST("receiver.do")
    @FormUrlEncoded
    Call<ResponseModel<String>> push(@Field("message") String message);

}
