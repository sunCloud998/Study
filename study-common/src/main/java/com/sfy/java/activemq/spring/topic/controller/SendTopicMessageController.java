package com.sfy.java.activemq.spring.topic.controller;

import com.sfy.java.activemq.spring.topic.provider.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: SendTopicMessageController.java
 * @Author: sunfayun
 * @Date: 2015/08/05
 * @Time: 下午9:11
 * @Version 1.0
 */
@Controller
@RequestMapping("/activemq")
public class SendTopicMessageController {

    @Autowired
    private TopicSender topicSender;

    @ResponseBody
    @RequestMapping("/topicSender")
    public String sendMessage(@RequestParam("message")String message){
        topicSender.send("topic_info",message);
        return "suc";
    }

}
