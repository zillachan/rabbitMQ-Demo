package com.unimedsci.mq.service;

import com.unimedsci.mq.common.ResponseModel;
import com.unimedsci.mq.config.RegisterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class RegisterServiceImpl implements RegisterService {

    private static final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);

    private APIService apiService;

    @Autowired
    private RegisterConfig registerConfig;

    @Override
    public void register() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(registerConfig.getRegisterAddress())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(APIService.class);
            Call<ResponseModel<String>> call = apiService.register(registerConfig.getLocalApiAddress(),
                    registerConfig.getExchangeName(), registerConfig.getRoutingKey(), registerConfig.getQueueName());
            call.enqueue(new Callback<ResponseModel<String>>() {
                @Override
                public void onResponse(Call<ResponseModel<String>> call, Response<ResponseModel<String>> response) {
                    if (response.isSuccessful() && 200 == response.body().getStatus()) {
                        log.info("注册成功 " + response.body().getMsg());
                    } else {
                        log.error("注册失败 " + response.body().getMsg());
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel<String>> call, Throwable t) {
                    log.error("注册失败 " + t.getMessage());
                }
            });
        }
    }
}
