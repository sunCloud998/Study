package com.sfy.java.exercise;

import com.google.common.collect.Lists;
import com.sfy.util.time.DateTimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
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

    @Test
    public void stringBuilderDeleteTest(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<10;i++){
            sb.append(i).append(",");
        }
        System.err.println(">>>"+sb.toString());
        sb = sb.deleteCharAt(sb.lastIndexOf(","));
        System.err.println("==>"+sb.toString());
    }

    @Test
    public void formatDateTimeTest(){
        String dateTime = "2017-02-28 00:00:00.000";//DateTimeUtil.currentTimeString();
        String newDateTime = this.formatDateTime(dateTime);
        System.err.println("====>"+newDateTime);
    }

    public String formatDateTime(String dateTime){
        if(StringUtils.isBlank(dateTime)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            String timeInfo = sdf.format(sdf.parse(dateTime));
            if(StringUtils.isNotBlank(timeInfo)){
                return timeInfo.replaceAll("-","/");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
