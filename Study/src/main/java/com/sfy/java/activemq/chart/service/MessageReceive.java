package com.sfy.java.activemq.chart.service;

import com.sfy.java.activemq.chart.domain.NoticeInfo;
import com.sfy.java.activemq.chart.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: MessageReceive.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午4:56
 * @Version 1.0
 */
public class MessageReceive {

    private static Logger logger = LoggerFactory
            .getLogger(MessageReceive.class);
    private JmsTemplate jmsTemplate;
    private NotifyMessageConverter messageConverter = new NotifyMessageConverter();
    private final List<NoticeInfo> noticeInfoList = new ArrayList<NoticeInfo>();
    private DefaultMessageListenerContainer dmcTopic;
    private DefaultMessageListenerContainer dmcQueue;
    private Topic notifyTopic;
    private Queue notifyQueue;


    /**
     * 接收消息（包括订阅和点对点）
     * @param loginUser 当前登录的用户
     * @param queueName 点多点接收人（自己）
     * @param topicName订阅接收人（订阅的主题）
     * @return
     */
    public List<NoticeInfo> receiveMSG(User loginUser,String queueName, String topicName) {

        try {

            if (notifyQueue == null) {

                notifyQueue = jmsTemplate.getConnectionFactory().createConnection()
                        .createSession(false, Session.AUTO_ACKNOWLEDGE)
                        .createQueue(queueName);
                runQueueMessageListener(loginUser,notifyQueue);

            }
            if (topicName != null) {

//String topicName2 = notifyTopic.getTopicName();
//System.out.println(!notifyTopic.getTopicName().equals(topicName));
                if (notifyTopic == null || !notifyTopic.getTopicName().equals(topicName)) {

                    notifyTopic = jmsTemplate.getConnectionFactory()
                            .createConnection()
                            .createSession(false, Session.AUTO_ACKNOWLEDGE)
                            .createTopic(topicName);
                    runTopicMessageListener(loginUser,notifyTopic);

                }

            }else {

                if (dmcTopic != null) {

                    dmcTopic.stop();

                }

            }
            List<NoticeInfo> noticeInfoListTemp = new ArrayList<NoticeInfo>();
            if (noticeInfoList.size() > 0) {

                noticeInfoListTemp.addAll(noticeInfoList);
                noticeInfoList.clear();

            }
            return noticeInfoListTemp;

        } catch (JMSException e) {

            return null;

        }

    }

    /**
     * 运行订阅模式的监听
     * @param loginUser 当前登录的用户
     * @param topic     订阅的主题
     */
    private void runTopicMessageListener(User loginUser,Topic topic) {

        if (dmcTopic != null) {

            try {

                String destinationName = dmcTopic.getDestinationName();
                if (!destinationName.equalsIgnoreCase(topic.getTopicName())) {

                    dmcTopic.stop();
                    dmcTopic = null;

                }

            } catch (JMSException e) {

                logger.error("方法:runTopicMessageListener 出错了", e);

            }

        }
        if (dmcTopic == null) {

            dmcTopic = new DefaultMessageListenerContainer();
            dmcTopic.setPubSubDomain(false);
            dmcTopic.setDestination(topic);
            dmcTopic.setConnectionFactory(this.jmsTemplate.getConnectionFactory());
            dmcTopic.setPubSubNoLocal(false);
            dmcTopic.setMessageListener(new listener(loginUser));
            dmcTopic.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
            dmcTopic.initialize();
            dmcTopic.start();

        }

    }
    /**
     * 运行点对点信息监听
     * @param loginUser 等前登录的用户
     * @param queue接受信息的人
     */
    private void runQueueMessageListener(User loginUser,Queue queue) {

        if (dmcQueue == null) {

            dmcQueue = new DefaultMessageListenerContainer();
            dmcQueue.setPubSubDomain(false);
            dmcQueue.setDestination(queue);
            dmcQueue.setConnectionFactory(this.jmsTemplate.getConnectionFactory());
            dmcQueue.setPubSubNoLocal(false);
            dmcQueue.setMessageListener(new listener(loginUser));
            dmcQueue.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
            dmcQueue.initialize();
            dmcQueue.start();

        }

    }

    /**
     * 停止监听
     */
    public void stopMessageListener() {

        if (dmcTopic != null) {

            dmcTopic.stop();

        }
        if (dmcQueue != null) {

            dmcQueue.stop();

        }

    }

    class listener implements MessageListener {


        private User user;

        public listener(User user){

            this.user = user;

        }

        @Override
        public void onMessage(Message message) {

            try {

                ObjectMessage objectMessage = (ObjectMessage) message;
                NoticeInfo noticeInfo1 = (NoticeInfo) messageConverter
                        .fromMessage(objectMessage);
                if (!user.getUsername().equals(noticeInfo1.getSender())) {

                    noticeInfoList.add(noticeInfo1);

                }
                System.out.println(user.getUsername()+"：此时list内有" + noticeInfoList.size() + "条消息");


            } catch (Exception e) {

// TODO: handle exception
                logger.error("处理信息时发生异常", e);

            }

        }
    }

    public JmsTemplate getJmsTemplate() {

        return jmsTemplate;

    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {

        this.jmsTemplate = jmsTemplate;

    }

    public NotifyMessageConverter getMessageConverter() {

        return messageConverter;

    }

    public void setMessageConverter(NotifyMessageConverter messageConverter) {

        this.messageConverter = messageConverter;

    }

// public List<NoticeInfo> getNoticeInfoList() {

// return noticeInfoList;
//
}
