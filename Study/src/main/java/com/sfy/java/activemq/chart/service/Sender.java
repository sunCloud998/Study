package com.sfy.java.activemq.chart.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * @Description: Sender.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午5:03
 * @Version 1.0
 */
public class Sender {

    private JmsTemplate jmsTemplate;

    private String topicName;

    private Topic topic;
    public void sendMessage(final String message) {


        try {

            if (topic == null) {

                topic = jmsTemplate.getConnectionFactory().createConnection()
                        .createSession(false, Session.AUTO_ACKNOWLEDGE)
                        .createTopic(topicName);

            }

            jmsTemplate.send(topic,new MessageCreator() {


                @Override
                public Message createMessage(Session session)
                        throws JMSException {


                    TextMessage textMessage = session.createTextMessage(message);
                    return textMessage;

                }

            });

        } catch (JMSException e) {

            e.printStackTrace();

        }

    }
}
