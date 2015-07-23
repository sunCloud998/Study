package com.sfy.java.activemq.chart.service;

import com.sfy.java.activemq.chart.domain.NoticeInfo;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * @Description: NotifyMessageConverter.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午4:59
 * @Version 1.0
 */
public class NotifyMessageConverter implements MessageConverter {

    private static Logger logger = LoggerFactory
            .getLogger(NotifyMessageConverter.class);

    @Override
/**
 * 转换接收到的消息为NoticeInfo对象
 */
    public Object fromMessage(Message message) throws JMSException,
            MessageConversionException {

// TODO Auto-generated method stub
        if (logger.isDebugEnabled()) {

            logger.debug("Receive JMS message :" + message);

        }
        if (message instanceof ObjectMessage) {

            ObjectMessage oMsg = (ObjectMessage) message;
            if (oMsg instanceof ActiveMQObjectMessage) {

                ActiveMQObjectMessage aMsg = (ActiveMQObjectMessage) oMsg;
                try {

                    NoticeInfo noticeInfo = (NoticeInfo) aMsg
                            .getObject();
                    return noticeInfo;

                } catch (Exception e) {
                throw new JMSException("Message:" + message.toString()
                        + "is not a instance of NoticeInfo."
                        + message.toString());

            }

        } else {
        throw new JMSException("Message:" + message.toString()
                + "is not a instance of ActiveMQObjectMessage."
                + message.toString());

    }

} else {
        throw new JMSException("Message:" + message.toString()
        + "is not a instance of ObjectMessage."
        + message.toString());

        }

        }

@Override
/**
 * 转换NoticeInfo对象到消息
 */
public Message toMessage(Object obj, Session session) throws JMSException,
        MessageConversionException {

// TODO Auto-generated method stub
        if (logger.isDebugEnabled()) {

        }
        if (obj instanceof NoticeInfo) {

        ActiveMQObjectMessage msg = (ActiveMQObjectMessage) session.createObjectMessage();
        msg.setObject((NoticeInfo) obj);
        return msg;

        } else {


        }
        return null;

        }
}
