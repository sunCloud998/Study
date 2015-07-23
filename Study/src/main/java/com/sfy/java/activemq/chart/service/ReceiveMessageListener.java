package com.sfy.java.activemq.chart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.*;

/**
 * @Description: ReceiveMessageListener.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午5:04
 * @Version 1.0
 */
public class ReceiveMessageListener implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(ReceiveMessageListener.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    private static String topicName1 = null;

    private  String msg_aa = "";

    private static ReceiveMessageListener messageListener = null;

    public static ReceiveMessageListener getInstance(String topicName) {


        if (messageListener == null || topicName1 != null || !topicName1.equals(topicName)) {

            messageListener = new ReceiveMessageListener();

        }
        return messageListener;

    }

    private ReceiveMessageListener() {


        Topic topic;
        if (topicName1 != null) {

            try {

                topic = this.jmsTemplate.getConnectionFactory().createConnection()
                        .createSession(false, Session.AUTO_ACKNOWLEDGE)
                        .createTopic(this.topicName1);

                DefaultMessageListenerContainer dmc = new DefaultMessageListenerContainer();
                dmc.setPubSubDomain(false);
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


    }

    public void onMessage(Message message) {

        if (message instanceof TextMessage) {

            TextMessage text = (TextMessage) message;
            try {

                setMsg_aa(msg_aa + text.getText());
                LOG.info("Received message:" + text.getText());
                System.out.println(text.getText());

            } catch (JMSException e) {

                e.printStackTrace();

            }

        }

    }

    public String getMsg_aa() {

        return msg_aa;

    }

    public void setMsg_aa(String msg_aa) {

        this.msg_aa = msg_aa;

    }

}
