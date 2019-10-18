package com.sfy.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.sfy.util.json.JsonMapper;
import com.sfy.util.time.DateTimeUtil;
import com.sfy.util.time.DateUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: JsonTest.java
 * @User: sunfayun
 * @Date: 2017/05/25
 * @Version: 1.0
 */
public class JsonTest {

    @Test
    public void test01(){
        List<PriceConfigRange> list = Lists.newArrayList();
        list.add(new PriceConfigRange(0,100,120));
        list.add(new PriceConfigRange(1,110,140));
        list.add(new PriceConfigRange(2,150,200));
        list.add(new PriceConfigRange(3,100,0));
        System.err.println("==>"+ JsonMapper.mapString(list));
    }

    @Data
    public static class PriceConfigRange {

        public PriceConfigRange(Integer type, Integer minRange, Integer maxRange) {
            this.type = type;
            this.minRange = minRange;
            this.maxRange = maxRange;
        }

        private Long id;
        private Integer type;
        private String name;
        @JsonProperty("min_range")
        private Integer minRange;
        @JsonProperty("max_range")
        private Integer maxRange;
        private Integer status;
        private Date createTime;

    }

    @Test
    public void test02(){
        String time = "Fri Oct 20 00:00:00 CST 2017";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(time);
            System.err.println("=>"+DateUtil.convert(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03(){
        //String str = "/Date(-2208988800000-0000)/";
        //String str = "/Date(2208988800000-0000)/";
        String str = "/Date(+1494250355473+0800)/";
        String regx = "/Date\\((\\D?\\d+)\\D\\d+\\)/";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
            long timeStamp = Long.parseLong(matcher.group(1));
            String time = DateTimeUtil.formatTime(timeStamp,"yyyy-MM-dd");
            System.err.println("===>"+time);
        }
    }
}
