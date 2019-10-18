package com.sfy.other;

import com.sfy.util.json.JsonMapper;
import com.sfy.util.time.DateUtil;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: DateFormatTest.java
 * @Date: 2016/04/06
 * @Author: sunfayun
 * @Version: 1.0
 */
public class DateFormatTest {

    @Test
    public void getDateTimeTest(){
        String time = "2015-06-19 21:09:02";
        String newTime = this.getFormatDateInfo(time,"yyyy-MM-dd");
        System.err.println("==>"+newTime);
    }

    @Test
    public void getDiffTest(){
        long value = 24*60*60*1000;
        try {
            String time = "2008-09-12";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date timeDate = sdf.parse(time);
            long diff = new Date().getTime() - timeDate.getTime();
            long test = diff / (value*365);
            System.err.println("=====>"+test);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whiteSpaceTest(){
        String test = "a sd bvsdg ds sdasfas      ";
        System.err.println("====>"+test.replaceAll(" ",""));
    }


    public String getFormatDateInfo(String dateTime, String dateFormat){
        try {
            if(StringUtils.isBlank(dateTime) || StringUtils.isBlank(dateFormat)){
                return null;
            }

            if(dateTime.contains("1900-01-01")){
                return null;
            }

            SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
            Date date;
            try {
                date = sdf.parse(dateTime);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
            if(null != date){
                String newDateTime = sdf.format(date);
                if(StringUtils.isNotBlank(newDateTime)){
                    newDateTime = newDateTime.replace("-","/");
                }
                return newDateTime;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void test01(){
        Integer[] arrTime = new Integer[]{120,9,18};

        Date date = new Date();
        int nowHour = Integer.parseInt(DateUtil.date2str(date,"HH"));
        int beginHour = arrTime[1];
        int endHour = arrTime[2];
        if(nowHour < beginHour || nowHour >= endHour){
            System.err.println("==>时间之外");
        }else{
            System.err.println("====>时间之内");
        }
    }

    @Test
    public void test02(){
        int x = 24-21;
        Date time = DateUtil.alterHour(new Date(),3);
        System.err.println("===>"+DateUtil.date2Str(time,"yyyy-MM-dd HH:mm:ss"));
        Date date = DateUtil.alterHour(time,x);
        System.err.println("获取今天的结束时间:"+DateUtil.date2Str(date,"yyyy-MM-dd"));
    }

    @Test
    public void test03(){
        List<HashMap<String,String>> list = new ArrayList<>();
        for(int i=0;i<1;i++){
            HashMap<String,String> map = new HashMap<>();
            map.put("id",String.valueOf(i));
            map.put("name","Test"+i);
            map.put("age",String.valueOf(20+i));
            list.add(map);
        }
        System.err.println("==>"+ JsonMapper.mapString(list));
    }

    @Test
    public void test04(){
        Map<String,String> map = new HashMap<>();
        map.put("id","1");
        map.put("name","Test");
        map.put("age","20");
        System.err.println("==>"+JsonMapper.mapString(map));
    }

}
