package com.sfy.java.activemq.chart.service;

import com.sfy.java.activemq.chart.domain.NoticeInfo;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @Description: MessageProducer.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午4:55
 * @Version 1.0
 */
public class MessageProducer {

    private JmsTemplate jmsTemplate;
    private NotifyMessageConverter messageConverter = new NotifyMessageConverter();

    /**
     * 发送点对点消息
     * @param noticeInfo  消息实体
     * @param destination 发送到哪
     * @return
     */
    public boolean sendQueue(NoticeInfo noticeInfo,String queueKey) {

        try {

            Destination notifyQueue = jmsTemplate.getConnectionFactory()
                    .createConnection()
                    .createSession(false, Session.AUTO_ACKNOWLEDGE)
                    .createQueue(queueKey);
            jmsTemplate.setMessageConverter(messageConverter);
            jmsTemplate.setPubSubDomain(false);
            jmsTemplate.convertAndSend(notifyQueue, noticeInfo);

        } catch (JMSException e) {

            return false;

        }
        return true;

    }
    /**
     * 发送订阅消息
     * @param noticeInfo  消息实体
     * @param destination 发送到哪
     * @return
     */
    public boolean sendTopic(NoticeInfo noticeInfo,String chatRoomKey) {

        try {

            Destination notifyTopic = jmsTemplate.getConnectionFactory()
                    .createConnection()
                    .createSession(false, Session.AUTO_ACKNOWLEDGE)
                    .createTopic(chatRoomKey);
            jmsTemplate.setMessageConverter(messageConverter);
            jmsTemplate.setPubSubDomain(false);
            jmsTemplate.convertAndSend(notifyTopic, noticeInfo);

        } catch (JMSException e) {

            return false;

        }
        return true;

    }


    public JmsTemplate getJmsTemplate() {

        return jmsTemplate;

    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {

        this.jmsTemplate = jmsTemplate;

    }


    public NotifyMessageConverter getMessageConverter() {

        return messageConverter;

    }

    public void setMessageConverter(NotifyMessageConverter messageConverter) {

        this.messageConverter = messageConverter;

    }

}
