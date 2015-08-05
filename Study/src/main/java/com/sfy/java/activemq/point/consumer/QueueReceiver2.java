package com.sfy.java.activemq.point.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Description: QueueReceiver2.java
 * @Author: sunfayun
 * @Date: 2015/08/05
 * @Time: 下午5:59
 * @Version 1.0
 */
@Service
public class QueueReceiver2 implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(QueueReceiver2.class);

    @Override
    public void onMessage(Message message) {
        try {
            System.err.println("QueueReceiver2接收到消息:" + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
