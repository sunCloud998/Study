package com.sfy.java.exercise;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;

/**
 * 测试集合为null的情况遍历
 * @author sunfayun
 * @time 2015/04/11 22:46
 */
public class NullArrayListLoopTest {

    @Test
    public void testEmptyList(){
        List<String> list = Lists.newArrayList();

        for(String s : list){
            System.err.println("=>"+s);
        }
    }

    @Test
    public void testNullList(){
        List<String> list = null;
        for(String s : list){
            System.err.println("=>"+s);
        }
    }

    @Test
    public void stringBuilderTest(){
        String test = "test";
        StringBuilder sb = new StringBuilder();
        sb.append(test);
        System.err.println("==>"+sb.lastIndexOf(","));
        if(StringUtils.isNotBlank(sb) && sb.lastIndexOf(",")>-1)
            System.err.println("====>"+sb.deleteCharAt(sb.lastIndexOf(",")));
    }

}
