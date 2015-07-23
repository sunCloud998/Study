package com.sfy.java.activemq.chart.service;

import com.sfy.java.activemq.chart.dao.ChatRoomDao;
import com.sfy.java.activemq.chart.domain.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: ChatRoomService.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午5:10
 * @Version 1.0
 */
@Service("chatRoomService")
public class ChatRoomService {
    @Autowired
    private ChatRoomDao chatRoomDao;


    public ChatRoom getByName(String chatRoomName){

        return chatRoomDao.getChatRoomByName(chatRoomName);

    }


    public List<ChatRoom> getAll(){

        return chatRoomDao.getAll();

    }


    public Integer insert(ChatRoom chatRoom){

        chatRoom.setCreateDate(new Date());
        return chatRoomDao.insert(chatRoom);

    }


    public ChatRoom getById(Integer chatRoomId) {

// TODO Auto-generated method stub
        return chatRoomDao.getChatRoomById(chatRoomId);

    }
}
