package com.sfy.java.activemq.chart.controller;

import com.sfy.java.activemq.chart.domain.ChatRoom;
import com.sfy.java.activemq.chart.domain.User;
import com.sfy.java.activemq.chart.service.ChatRoomService;
import com.sfy.java.activemq.chart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description: ChatRoomController.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午4:15
 * @Version 1.0
 */
@Controller
public class ChatRoomController {

    @Autowired
    private ChatRoomService roomService;
    @Autowired
    private UserService userService;

    @RequestMapping("roomList.do")
    public String roomList(Map<String, Object> map) {

        List<ChatRoom> list = roomService.getAll();
        map.put("roomList", list);
        return "room_list";

    }

    @RequestMapping("roomView.do")
    public String roomView(HttpServletRequest request,
                           HttpServletResponse response, Integer roomId, Map<String, Object> map) {

        User user = (User) request.getSession().getAttribute("user");
        List<User> otherUser = userService.getOtherUser(user.getId());
        map.put("otherUser", otherUser);
        map.put("user", user);
        return "room_view1";

    }

}
