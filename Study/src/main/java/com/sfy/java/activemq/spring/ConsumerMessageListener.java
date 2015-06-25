package com.sfy.java.activemq.spring;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @Author: sunfayun
 * @Date: 2015/06/24
 * @Time: 下午1:35
 * Description: ConsumerMessageListener.java
 */
public class ConsumerMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        System.err.println("接收者收到消息。。。。。。。");
        try {
            System.err.println("|_消息内容:"+mapMessage.getLong("count"));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
