package com.sfy.java.activemq.point;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/21.
 */
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.consumerTest();
    }

    /**
     * 在点对点的传输方式中，消息数据被持久化，每条消息都能被消费，没有监
     * 听 QUEUE 地址也能被消费，数据不会丢失，一对一的发布接受策略，保证数据完整
     */
    public void consumerTest(){
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        String subject = "TOOL.DEFAULT";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user,password,url);
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            final Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);
            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        MapMessage mapMessage = (MapMessage) message;
                        System.err.println("--收到消息："+new Date(mapMessage.getLong("count")));
                        session.commit();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                }
            });
            Thread.sleep(30000);
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
