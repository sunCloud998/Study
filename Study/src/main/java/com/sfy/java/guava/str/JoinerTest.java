package com.sfy.java.guava.str;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author sunfayun
 * @time 2015/04/02 13:38
 */
@Setter @Getter
public class JoinerTest {

    private List<User> userList ;

    @Setter @Getter @NoArgsConstructor @AllArgsConstructor
    public static class User{
        private String name;
    }

    public static void main(String[] args) {
        JoinerTest joinerTest = new JoinerTest();
        List<User> list = Lists.newArrayList();
        list.add(new User("aa"));
        list.add(new User("bb"));
        list.add(new User("cc"));
        list.add(new User("dd"));
        joinerTest.setUserList(list);

        String result = Joiner.on(",").join(joinerTest.getUserList());
        System.err.println("==>"+result);
    }
}
