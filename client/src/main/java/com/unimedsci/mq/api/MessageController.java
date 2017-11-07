package com.unimedsci.mq.api;

import com.unimedsci.mq.common.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/client")
public class MessageController {

    /**
     * 往exchange 中添加消息
     *
     * @param message 消息内容
     * @return
     */
    @PostMapping(path = "/receiver.do")
    public @ResponseBody
    ResponseModel<String> receiveMessage(@RequestParam String message) {
        log.info("received message:" + message);
        return new ResponseModel<>(message);
    }
}
