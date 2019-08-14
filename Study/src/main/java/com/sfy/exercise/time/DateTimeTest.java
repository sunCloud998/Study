package com.sfy.exercise.time;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @function:
 * @description: DateTimeTest.java
 * @date: 2018/04/16
 * @author: sunfayun
 * @version: 1.0
 */
public class DateTimeTest {

    @Test
    public void test01(){
        Date date = new Date();
        //date类型转成毫秒
        long time = date.getTime();
        System.err.println("=>"+time);

        String timeStr = "2018-04-13 17:52:30";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = sdf.parse(timeStr);
            long time1 = date1.getTime();
            //String类型转成时间戳
            System.err.println("==>"+time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeStamp = System.currentTimeMillis();
        //时间戳转成String日期类型
        String dateTime = sdf1.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        System.err.println("==>"+dateTime);
    }

    @Test
    public void test02(){
        FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyyMMddHHmmss");
        String time = fastDateFormat.format(System.currentTimeMillis());
        System.err.println("=>"+time);
    }

    @Test
    public void test03() {
        String result = getTime(86399);
        System.err.println("=>"+result);
    }

    private String getTime(int second) {
        int hour = second/3600;
        int minute = (second - hour*3600)/60;
        second = second-hour*3600-minute*60;

//        Integer hours =(int) (s/(60*60));
//        Integer minutes = (int) (s/60-hours*60);
//        Integer seconds = (int) (s-minutes*60-hours*60*60);


        return hour + ":" + minute + ":" + second;

    }

}
