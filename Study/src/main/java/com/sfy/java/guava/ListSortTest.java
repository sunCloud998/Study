package com.sfy.java.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.List;

/**
 * Title: ListSortTest.java
 *
 * @Date: 2015/05/12
 * @Time: 下午5:23
 * @Author: sunfayun
 */
public class ListSortTest {

    @Test
    public void sortListTest(){
        List<User> list = Lists.newArrayList();
        User user = new User();
        user.setName("test1");
        user.setAge(27);
        list.add(user);

        User user1 = new User();
        user1.setName("test2");
        user1.setAge(22);
        list.add(user1);

        User user3 = new User();
        user3.setName("test3");
        user3.setAge(21);
        list.add(user3);

        User user4 = new User();
        user4.setName("test4");
        user4.setAge(29);
        list.add(user4);

        Ordering<User> ordering = new Ordering<User>() {
            @Override
            public int compare(User user, User t1) {
                return Integer.compare(user.getAge(),t1.getAge());
            }
        };
        List<User> userList = ordering.sortedCopy(list);
        for(User u : userList){
            System.err.println(u.toString());
        }
    }

    @Data
    @ToString
    public static class User{
        private String name;
        private Integer age;
    }

}
