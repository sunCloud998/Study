package com.sfy.java.activemq.chart.service;

import com.sfy.java.activemq.chart.dao.UserDao;
import com.sfy.java.activemq.chart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: UserService.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午5:11
 * @Version 1.0
 */
@Service("userService")
public class UserService {
    @Autowired
    public UserDao userDao;

    public User getUser(String username, String password){

        return userDao.getUser(username, password);

    }

    public List<User> getOtherUser(Integer id){

        return userDao.getOtherUser(id);

    }

    public User getUserById(Integer id){

        return userDao.getUserById(id);

    }
}
