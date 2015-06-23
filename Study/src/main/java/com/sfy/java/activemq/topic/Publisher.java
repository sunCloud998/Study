package com.sfy.java.activemq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/21.
 */
public class Publisher {

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.publisherTest();
    }

    public void publisherTest(){
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = "tcp://localhost:61616";
        String subject = "TOOL.DEFAULT";

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user,password,url);

        Connection connection;

        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(subject);
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            for (int i=0; i<=20; i++){
                MapMessage mapMessage = session.createMapMessage();
                Date date = new Date();
                mapMessage.setLong("count",date.getTime());
                Thread.sleep(1000);
                producer.send(mapMessage);
                System.err.println("--发送消息："+date);
            }
            session.commit();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
