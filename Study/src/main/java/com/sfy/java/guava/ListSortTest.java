package com.sfy.java.guava;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
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

    @Test
    public void testFilter(){
        List<String> newList = new ArrayList(){{
           add("a");
            add("b");
            add("c");
            add("d");
            add("e");
            add("f");
            add("g");
        }};

        List<String> oldList = new ArrayList(){{
            add("a");
            add("s");
        }};

        Collection<String> target = Collections2.filter(oldList, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String s) {
                return !newList.contains(s);
            }
        });
        List<String> list = Lists.newArrayList(target);
        System.err.println("==>"+list.toString());
    }

    @Test
    public void testCollection(){
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        for (int i=0;i<1000;i++){
            Collection<Integer> collection = Collections2.filter(list, new Predicate<Integer>() {
                @Override
                public boolean apply(@Nullable Integer input) {
                    return input != 3;
                }
            });
            List<Integer> newList = Lists.newArrayList(collection);
            System.err.println("==>"+newList);
        }

    }

    public static enum TestEnum{

        A(1,"a"),;

        private Integer type;
        private String name;

        TestEnum(Integer type,String name){
            this.type = type;
            this.name = name;
        }
    }

}
