package com.sfy.java.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/23.
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void receive() {
        MapMessage message = (MapMessage) jmsTemplate.receive();
        try {
            System.err.println("--收到消息："+new Date(message.getLong("count")));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
