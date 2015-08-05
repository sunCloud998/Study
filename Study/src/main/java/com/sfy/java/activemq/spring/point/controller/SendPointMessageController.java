package com.sfy.java.activemq.spring.point.controller;

import com.sfy.java.activemq.spring.point.producer.QueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: SendPointMessageController.java
 * @Author: sunfayun
 * @Date: 2015/08/05
 * @Time: 下午6:15
 * @Version 1.0
 */
@Controller
@RequestMapping("/activemq")
public class SendPointMessageController {

    @Autowired
    private QueueSender queueSender;

    @ResponseBody
    @RequestMapping(value = "/queueSender")
    public String sendMessage(@RequestParam("message")String message){
        queueSender.send("queue_info",message);
        return "suc";
    }

}
