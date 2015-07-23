package com.sfy.java.activemq.chart.dao;

import com.sfy.java.activemq.chart.domain.ChatRoom;

import java.util.List;

/**
 * @Description: ChatRoomDao.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午4:29
 * @Version 1.0
 */
public interface ChatRoomDao {

    ChatRoom getChatRoomByName(String chatRoomName);

    List<ChatRoom> getAll();

    Integer insert(ChatRoom chatRoom);

    ChatRoom getChatRoomById(Integer chatRoomId);

}
