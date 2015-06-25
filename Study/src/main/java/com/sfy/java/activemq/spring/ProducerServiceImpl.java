package com.sfy.java.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/23.
 */
@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination destination;

    @Override
    public void send() {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                Date date = new Date();
                message.setLong("count",date.getTime());
                System.err.println("--发送消息:"+date);

                return message;
            }
        };
        jmsTemplate.send(this.destination,messageCreator);
    }

}
