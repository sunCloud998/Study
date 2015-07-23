package com.sfy.java.activemq.chart.controller;

import com.sfy.java.activemq.chart.domain.User;
import com.sfy.java.activemq.chart.service.CommonUtil;
import com.sfy.java.activemq.chart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description: LoginController.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午4:19
 * @Version 1.0
 */
@Controller("loginController")
public class LoginController {

        @Autowired
        private UserService userService;

        @RequestMapping(value="/login.do",method= RequestMethod.POST)
        public String login(String username, String password,
                            HttpServletRequest request, HttpServletResponse response,
                            Map<String, Object> map) {

            User user = userService.getUser(username, CommonUtil.MD5(password));
            if (user != null) {

                request.getSession().setAttribute("user", user);

                List<User> otherUser = userService.getOtherUser(user.getId());

                map.put("otherUser", otherUser);
                map.put("user", user);
                return "redirect:roomView.do";

            }
            return "login";

        }

}
