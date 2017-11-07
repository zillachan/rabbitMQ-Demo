package com.unimedsci.mq.api;

import com.unimedsci.mq.common.ResponseModel;
import com.unimedsci.mq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/service")
public class MessageController {
    @Autowired
    private MessageService messageService;
    /**
     * 往exchange 中添加消息
     *
     * @param exchangeName exchange名称
     * @param routingKey   路由名称
     * @param message      消息内容
     * @return
     */
    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseModel<String> pushMessage(@RequestParam String exchangeName, @RequestParam String routingKey, @RequestParam String message) {
        return messageService.addMessage(exchangeName, routingKey, message);
    }
}
