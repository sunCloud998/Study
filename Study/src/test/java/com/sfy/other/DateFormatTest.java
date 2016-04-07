package com.sfy.other;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

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

}
