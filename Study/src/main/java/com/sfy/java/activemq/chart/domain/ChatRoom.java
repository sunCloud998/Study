package com.sfy.java.activemq.chart.domain;

import java.util.Date;

/**
 * @Description: ChatRoom.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午5:06
 * @Version 1.0
 */
public class ChatRoom {

    private Integer id;//房间id
    private String chatRoomName;//房间名称
    private Date createDate;//创建时间
    private Integer creatorId;//创建者ID

    private User creator;//创建者


    public Integer getId() {

        return id;

    }
    public void setId(Integer id) {

        this.id = id;

    }
    public String getChatRoomName() {

        return chatRoomName;

    }
    public void setChatRoomName(String chatRoomName) {

        this.chatRoomName = chatRoomName;

    }
    public User getCreator() {

        return creator;

    }
    public void setCreator(User creator) {

        this.creator = creator;

    }
    public Date getCreateDate() {

        return createDate;

    }
    public void setCreateDate(Date createDate) {

        this.createDate = createDate;

    }
    public Integer getCreatorId() {

        return creatorId;

    }
    public void setCreatorId(Integer creatorId) {

        this.creatorId = creatorId;

    }
}
