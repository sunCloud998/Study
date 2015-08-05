package com.sfy.java.activemq.spring.topic.consumer;

import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 使用和spring集成的方式创建发布订阅消息接收者
 * @Description: TopicReceiver2.java
 * @Author: sunfayun
 * @Date: 2015/08/05
 * @Time: 下午8:43
 * @Version 1.0
 */
@Service
public class TopicReceiver2 implements MessageListener {
    
    @Override
    public void onMessage(Message message) {
        try {
            String messageInfo = ((TextMessage)message).getText();
            System.err.println("TopicReceiver2接收到消息:"+messageInfo);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
