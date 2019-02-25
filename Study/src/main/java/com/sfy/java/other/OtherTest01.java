package com.sfy.java.other;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sfy.util.thread.ThreadPool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Function:
 * @Description: OtherTest01.java
 * @Date: 2017/09/04
 * @Author: sunfayun
 * @Version: 1.0
 */
public class OtherTest01 {

    @Test
    public void test03(){
        final List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);

        final List<Integer> listTwo = Lists.newArrayList();
        for(int i=0;i<15;i++){
            listTwo.add(i);
        }
        listTwo.addAll(list);
        System.err.println("===>"+listTwo);
    }

    @Test
    public void test04(){
        try {
            int x = 100 / 0;
        } catch (ArithmeticException ae) {
            System.err.println("ArithmeticException");
        } catch (Exception e){
            e.printStackTrace();
            System.err.println("Exception");
        }
    }

    @Test
    public void test05(){
        Person person01 = new Person(1,"zhangsan",22);
        Person person02 = new Person(2,"lisi",23);

        int result = ComparisonChain.start()
                .compare(person01.getName(),person02.getName())
                .compare(person01.getAge(),person02.getAge())
                .result();
        System.err.println("==>"+result);
    }

    @Test
    public void test06(){
        int x = 1;
        switch (x) {
            case 0:
                System.err.println("0");
            case 1:
                System.err.println("1");
            case 2:
                System.err.println("2");
            case 3:
                System.err.println("3");
        }
    }

    @Test
    public void booleanEqualsTest(){
        boolean oldValue = true;
        boolean newValue = true;
        if(oldValue == newValue) {
            System.err.println("==>值相等");
        }else {
            System.err.println("==>值不等");
        }
    }

    @Test
    public void threadExceptionTest(){
        try {
            int x = 100;
            ThreadPool.execute(() -> {
                System.err.println(Thread.currentThread().getName()+"==>线程池方法开始执行");
                int y = x / 0;
                System.err.println(Thread.currentThread().getName()+"==>y:"+y);
                System.err.println(Thread.currentThread().getName()+"==>线程池方法执行结束");
            });
            Thread.sleep(1000);
            int result = x / 10;
            System.err.println(Thread.currentThread().getName()+"===>"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person{
        private Integer id;
        private String name;
        private Integer age;
    }

    @Test
    public void atomicTest(){
        AtomicLong atomicLong = new AtomicLong();
        System.err.println("==>"+atomicLong.get());
    }

    @Test
    public void nullTest(){
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        System.err.println("==>"+sb.toString());
    }

    @Test
    public void calendarTest(){
        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();
        System.err.println("===>"+ time);
    }

    @Test
    public void dateTest02(){
        String yearMonth = "";
        String month = "";
        Calendar cal = Calendar.getInstance();
        int nowMonth = cal.get(Calendar.MONTH)+2;
        int year = cal.get(Calendar.YEAR);
        if(nowMonth > 0 && nowMonth < 10) {
            month = "-"+"0"+nowMonth;
        }else if(nowMonth == 13) {
            month = "-"+"01";
            year = year + 1;
        } else {
            month = "-"+nowMonth;
        }
        yearMonth = year + month;
        System.err.println(yearMonth);
    }

    @Test
    public void test07(){
        String str1 = "alter table zcm_qrcode_change_shift";
        String str2 = " add `sum_data` varchar(2048) not null default '' comment '汇总数据' after `end_time`;";
        for(int i=0;i<100;i++) {
            String sql = str1+i+str2;
            System.err.println(sql);
        }
    }

    @Test
    public void test08(){
        Map<String,String> map = Maps.newHashMap();
        map.put("other/other/123","345");
        String test = map.get(123);
        System.err.println("test:"+test);
    }

    @Test
    public void test09() {
        String test = "";

    }

}
