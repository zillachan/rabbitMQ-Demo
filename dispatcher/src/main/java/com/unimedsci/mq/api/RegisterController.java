package com.unimedsci.mq.api;

import com.unimedsci.mq.common.ResponseModel;
import com.unimedsci.mq.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/messages")
public class RegisterController {
    @Autowired
    private RegisterService registerService;


    /**
     * 往exchange 中添加消息
     *
     * @param apiAddress   接口地址
     * @param exchangeName
     * @param routingKey   Topic
     * @param queueName
     * @return
     */
    @PostMapping(path = "/register")
    public @ResponseBody
    ResponseModel<String> register(@RequestParam String apiAddress, @RequestParam String exchangeName, @RequestParam String routingKey, @RequestParam String queueName) {
        return registerService.register(apiAddress, exchangeName, routingKey,queueName);
    }

    /**
     * unregister
     *
     * @param apiAddress
     * @param exchangeName
     * @param routingKey
     * @return
     */
    @PostMapping(path = "/unregister")
    public @ResponseBody
    ResponseModel<String> unregister(@RequestParam String apiAddress, @RequestParam String exchangeName, @RequestParam String routingKey) {
        return registerService.unregister(apiAddress, exchangeName, routingKey);
    }
}
