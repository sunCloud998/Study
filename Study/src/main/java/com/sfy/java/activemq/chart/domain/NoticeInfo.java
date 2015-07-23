package com.sfy.java.activemq.chart.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sfy.java.activemq.chart.service.JsonDateSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: NoticeInfo.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午4:57
 * @Version 1.0
 */
public class NoticeInfo implements Serializable {

    private static final long serialVersionUID = -6087841224093836048L;
    public static final String SEND_TYPE_SYSTEM = "system";
    public static final String SEND_TYPE_QUEUE = "queue";
    public static final String SEND_TYPE_TOPIC = "topic";
    /** 消息发送者 */
    private String sender;
    private Integer senderId;
    /** 消息内容 */
    private String noticeContent;
    /** 接收者 */
    private String receiver;
    private Integer receiverId;
    /** 消息发送日期 */
    private Date sendDate;

    private String type;  //消息类型 ‘system’管理员  ‘queue’点对点私聊  ‘topic’群聊


    public String getNoticeContent() {

        return noticeContent;

    }

    public void setNoticeContent(String noticeContent) {

        this.noticeContent = noticeContent;

    }

    public String getReceiver() {

        return receiver;

    }

    public void setReceiver(String receiver) {

        this.receiver = receiver;

    }


    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getSendDate() {

        return sendDate;

    }

    public void setSendDate(Date sendDate) {

        this.sendDate = sendDate;

    }

    public String getSender() {

        return sender;

    }

    public void setSender(String sender) {

        this.sender = sender;

    }

    public String getType() {

        return type;

    }

    public void setType(String type) {

        this.type = type;

    }

    public Integer getSenderId() {

        return senderId;

    }

    public void setSenderId(Integer senderId) {

        this.senderId = senderId;

    }

    public Integer getReceiverId() {

        return receiverId;

    }

    public void setReceiverId(Integer receiverId) {

        this.receiverId = receiverId;

    }

}
