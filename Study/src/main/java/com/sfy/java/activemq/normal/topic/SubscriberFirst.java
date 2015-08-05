package com.sfy.java.activemq.normal.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * 使用普通的方式发送消息，不和spring集成
 */
public class SubscriberFirst {

    public static void main(String[] args) throws JMSException {
        SubscriberFirst subscriberFirst = new SubscriberFirst();
        subscriberFirst.subscriberFirstTest();
    }

    public void subscriberFirstTest() throws JMSException {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = "tcp://localhost:61616";
        String subject = "TOOL.DEFAULT";

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user,password,url);

        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(subject);
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    MapMessage mapMessage = (MapMessage) message;

                    try {
                        System.err.println("--订阅者1收到消息："+new Date(mapMessage.getLong("count")));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                connection.close();
            }
        }
    }

}
