package com.sfy.other;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author suncloud
 */
public class AllTest {

    @Test
    public void test01() {
        DateTime dateTime = new DateTime(1508766132696L);
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        String result = dateTime.toString(format);
        System.err.println("=>"+result);
    }

    @Test
    public void test02() {
        String regx = "[a-zA-Z\\d]+";
        Pattern pattern = Pattern.compile(regx);
        String as = "http://www.baidu.com";
        String bs = "180730ls13b3fb4s0";
        Matcher matcher = pattern.matcher(bs);
        if(matcher.matches()){
            System.err.println("匹配成功");
        } else {
            System.err.println("匹配失败");
        }
    }

    @Test
    public void test03() {
        System.err.println(Integer.MIN_VALUE + 1);
        System.err.println(Integer.MIN_VALUE);
        System.err.println(0x80000001);

        List<Integer> list = new ArrayList<Integer>() {{
           add(0x80000000);
           add(0x80000001);
        }};
        if(list.contains(Integer.MIN_VALUE + 1)) {
            System.err.println("contains");
        }
    }

}

