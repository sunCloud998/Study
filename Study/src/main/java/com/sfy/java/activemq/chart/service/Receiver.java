package com.sfy.java.activemq.chart.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.*;

/**
 * @Description: Receiver.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午5:07
 * @Version 1.0
 */
public class Receiver implements MessageListener {

    private JmsTemplate jmsTemplate;

    private String topicName;

    public Receiver(JmsTemplate jmsTemplate,String topicName){


        this.jmsTemplate = jmsTemplate;

        this.topicName = topicName;

        Topic topic;
        try {

            topic = this.jmsTemplate.getConnectionFactory().createConnection().createSession(false,
                    Session.AUTO_ACKNOWLEDGE).createTopic(this.topicName);

            DefaultMessageListenerContainer dmc = new DefaultMessageListenerContainer();
            dmc.setPubSubDomain(true);
            dmc.setDestination(topic);
            dmc.setConnectionFactory(this.jmsTemplate.getConnectionFactory());
            dmc.setPubSubNoLocal(true);
            dmc.setMessageListener(this);
            dmc.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
            dmc.initialize();
            dmc.start();

        } catch (JMSException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void onMessage(Message message) {


        System.out.println(message);

    }
}
