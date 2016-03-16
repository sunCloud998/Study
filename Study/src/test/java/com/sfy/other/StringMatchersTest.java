package com.sfy.other;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: StringMatchersTest.java
 * @Date: 2016/01/25
 * @Author: sunfayun
 * @Version: 1.0
 */
public class StringMatchersTest {

    @Test
    public void matcherTest(){
        String str = "[0-9a-zA-Z]{9,20}";
        String regx = "213993190213 ";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(regx);
        if(matcher.matches()){
            System.err.println("===>hahahahahaha");
        }
        boolean flag = str.matches("爆款");
        boolean flag1 = "爆款".matches(str);
        System.err.println("===>"+flag);
        System.err.println("===>"+flag1);

        String test = "测试测试|章节";
        String newString = test.substring(test.indexOf("|")+1,test.length());
        System.err.println("=====>"+newString);


    }

}
