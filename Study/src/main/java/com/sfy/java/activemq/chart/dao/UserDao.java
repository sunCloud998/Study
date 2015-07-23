package com.sfy.java.activemq.chart.dao;

import com.sfy.java.activemq.chart.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: UserDao.java
 * @Author: sunfayun
 * @Date: 2015/07/14
 * @Time: 下午4:54
 * @Version 1.0
 */
@Repository
public interface UserDao {

    User getUser(@Param("username") String username, @Param("password")String password);

    List<User> getOtherUser(Integer id);

    User getUserById(Integer id);
}
