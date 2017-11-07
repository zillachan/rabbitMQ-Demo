package com.unimedsci.mq.spring.web;

import com.unimedsci.mq.common.ResponseModel;
import com.unimedsci.mq.spring.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 消息生产接口。<br>
 * 如何使用：
 * <p>
 * POST /mq/pushMessage?exchangeName=unimedsci&routingKey=topic.message&message=Hello from paw.
 * <br>
 * POST /mq/pushMessage?exchangeName=unimedsci.broadcaust&routingKey&message=broadcast hello from paw.
 * </p>
 */
@Controller
@RequestMapping("/mq")
public class ProducerController {

    @Autowired
    MQService mqService;

    /**
     * 生产消息
     *
     * @param exchangeName 约定暂时支持两个：1.unimedsci用于发送普通消息；2.unimedsci.broadcast用于广播消息
     * @param routingKey   分发规则
     * @param message      消息内容
     * @return {status:200,msg:success,data:xxx},如果状态码不是200，则发生错误
     */
    @PostMapping(path = "/pushMessage")
    public @ResponseBody
    ResponseModel<String> pushMessage(@RequestParam String exchangeName,
                                      @RequestParam String routingKey,
                                      @RequestParam String message) {
        return mqService.sendMessage(exchangeName, routingKey, message);
    }
}