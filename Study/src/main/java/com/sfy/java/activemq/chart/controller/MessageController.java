package com.sfy.java.activemq.chart.controller;

import com.sfy.java.activemq.chart.domain.NoticeInfo;
import com.sfy.java.activemq.chart.domain.User;
import com.sfy.java.activemq.chart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: MessageController.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午4:21
 * @Version 1.0
 */
@Controller("messageController")
@Scope("session")
public class MessageController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private MessageReceive messageReceive;
    @Autowired
    private ChatRoomService roomService;

    /**
     * 发送消息
     *
     * @param request
     * @param response
     * @param userid    私聊时发送对象
     * @param queue
     * @param topicName 群聊是房间名称
     * @return
     */
    @RequestMapping("/sendMSG.do")
    @ResponseBody
    public final Map<String, Object> sendMSG(HttpServletRequest request,
                                             String chatTo, String message, Integer userid, Integer chatRoomId, Integer queue) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("msg", "未登录");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {

            NoticeInfo noticeInfo = new NoticeInfo();
            noticeInfo.setSender(user.getUsername());
            noticeInfo.setSenderId(user.getId());
            noticeInfo.setSendDate(new Date());

            if (!chatTo.isEmpty() && userid != null) {

                message = message.replaceFirst("你对" + chatTo + "说:", "");
                noticeInfo.setReceiver(chatTo);
                noticeInfo.setReceiverId(userid);
                noticeInfo.setNoticeContent(message);

            } else {

                noticeInfo.setNoticeContent(message);

            }


            boolean sendMessage = false;
            if (!chatTo.isEmpty() && queue != null && queue == 1 && userid != null) {
//私聊
                noticeInfo.setType(NoticeInfo.SEND_TYPE_QUEUE);
                sendMessage = messageProducer.sendQueue(noticeInfo, CommonUtil.getQueueKey(userid));

            } else {
//群聊
                String chatRoomKey = CommonUtil.MD5("60聊天室");
                noticeInfo.setType(NoticeInfo.SEND_TYPE_TOPIC);
                sendMessage = messageProducer.sendTopic(noticeInfo, chatRoomKey);

            }
            if (sendMessage) {

                map.put("success", true);
                map.put("msg", noticeInfo);

            } else {

                map.put("success", false);
                map.put("msg", "发送失败");

            }

        }

        return map;

    }

    @RequestMapping("/recvMSG.do")
    @ResponseBody
    public final Map<String, Object> recvMSG(HttpServletRequest request,
                                             HttpServletResponse response, Integer chatRoomId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {

            String chatRoomKey = CommonUtil.MD5("60聊天室");
            List<NoticeInfo> receiveQueueList = messageReceive.receiveMSG(user, CommonUtil.getQueueKey(user.getId()), chatRoomKey);
            if (receiveQueueList.size() > 0) {

                map.put("success", true);
                map.put("noticeList", receiveQueueList);
                map.put("user", user);

            }

        } else {

            map.put("msg", "未登录");

        }

        return map;

    }

    @RequestMapping("/exit.do")
    @ResponseBody
    public final void exit(HttpServletRequest request,
                           HttpServletResponse response) {

        messageReceive.stopMessageListener();

    }

}
