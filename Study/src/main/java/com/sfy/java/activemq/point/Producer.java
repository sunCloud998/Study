package com.sfy.java.activemq.point;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Date;

/**
 * @Author: sunfayun
 * @Date: 2015/06/19
 * @Time: 下午7:12
 * Description: Producer.java
 */
public class Producer {

    private final static Logger logger = LoggerFactory.getLogger(Producer.class);

    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private String subject = "TOOL.DEFAULT";

    @Test
    private void producerTest(){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user,password,url);

        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(subject);
            MessageProducer producer = session.createProducer(destination);

            for(int i=0;i<=20;i++){
                MapMessage mapMessage = session.createMapMessage();
                Date date = new Date();
                mapMessage.setLong("Count:",date.getTime());
                Thread.sleep(1000);
                producer.send(mapMessage);
                System.err.println("发送消息："+date);
            }

            session.commit();
            session.close();
            connection.close();

        } catch (JMSException e) {
            logger.error("创建连接产生异常",e);
        } catch (InterruptedException e) {
            logger.error("线程休眠发生异常",e);
        }
    }

}
