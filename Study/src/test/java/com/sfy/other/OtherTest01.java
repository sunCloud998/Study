package com.sfy.other;

import com.google.common.base.Splitter;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sfy.util.json.JsonUtils;
import com.sfy.util.thread.ThreadPool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

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

    @Test
    public void test10() {
        final Pattern ALIPAY_PATTERN = Pattern.compile("((2[5-9])|(30))\\d{14,22}$");
        final Pattern WXPAY_PATTERN = Pattern.compile("1[0-5]\\d{16}$");
        final Pattern UNIONPAY_CODE_REGEX = Pattern.compile("^(62)\\d{17}$");

        String authCode = "6255479014917558493";

        if(ALIPAY_PATTERN.matcher(authCode).matches()) {
            System.out.println("支付宝-matches");
        } else if(WXPAY_PATTERN.matcher(authCode).matches()) {
            System.out.println("微信-matches");
        } else if(UNIONPAY_CODE_REGEX.matcher(authCode).matches()) {
            System.out.println("银联二维码-matches");
        } else {
            System.out.println("异常-matches");
        }

        if(ALIPAY_PATTERN.matcher(authCode).find()) {
            System.out.println("支付宝-find");
        } else if(WXPAY_PATTERN.matcher(authCode).find()) {
            System.out.println("微信-find");
        } else if(UNIONPAY_CODE_REGEX.matcher(authCode).find()) {
            System.out.println("银联二维码-find");
        } else {
            System.out.println("异常-find");
        }
    }

    @Test
    public void test11() {
        String source = "20,19,12,16,29,20,20,14,20,19,19,20,12,14,17,15,16,17,16,12,16,21,12,30,15,24,16,9,16,18,14,15,19,13,17,13,8,13,12,13,13,9,11,12,6,6,8,7,9,18,16,12,13,11";
        List<String> list = Splitter.on(",").splitToList(source);
        int target = 0;
        for (String s : list) {
            target += Integer.parseInt(s);
        }
        System.err.println(target);
    }

    @Test
    public void test12() {
        String[] test = null;
        for (String s : test) {
            System.err.println(s);
        }
    }

    @Test
    public void test13() {
        String[] indrx = {"1","2","3"};
        String[] maitu_index = {"11","22"};
        String[] maitu_sum = {"111","222"};
        String[] maitu_rival = {"111","222", "sss"};
        String[] maitu_rate = {"111","222"};
        String[] maitu_starttime = {"111","222","222", "sss"};
        String[] maitu_endtime = {"111","222"};


        String budgetNumber = "111,222,222, sss,222,222, sss,222,222, sss";
        String[] arrBudget = budgetNumber.split(",");
        String[] subArrBudget = new String[0];
        if(arrBudget != null) {
            if(arrBudget.length < 8) {
                subArrBudget = arrBudget;
            } else {
                List<String> list = Arrays.asList(arrBudget).subList(0,8);
                subArrBudget = list.toArray(arrBudget);
            }
        }
        List<Map<String,String>> list = new ArrayList<>();
        if(subArrBudget != null && subArrBudget.length > 0) {
            for (String budget : subArrBudget) {
                Map<String,String> budgetMap = new HashMap<>();
                budgetMap.put("budget",budget);
                list.add(budgetMap);
            }
        }
        System.err.println(JsonUtils.toJson(list));



//        List<String> list = Arrays.asList(arrBudget).subList(0,2);
//        arrBudget = list.toArray(arrBudget);
//        System.err.println(JsonUtils.toJson(arrBudget));

//        List<Integer> list = new ArrayList<Integer>() {{
//           add(indrx.length);
//           add(maitu_index.length);
//           add(maitu_sum.length);
//           add(maitu_rival.length);
//           add(maitu_rate.length);
//           add(maitu_starttime.length);
//           add(maitu_endtime.length);
//        }};
//        Collections.sort(list);
//        System.err.println(list.get(list.size() -1));
//
//        Map<String,List<Map<String,String>>> map = new HashMap<>();
//        map.put("data", convert2ListMap(indrx, maitu_index, maitu_sum,maitu_rival,maitu_rate,maitu_starttime,maitu_endtime));
//        System.err.println(JsonUtils.toJson(map));
    }

    private List<Map<String,String>> convert2ListMap(String[] indrx, String[] maitu_index, String[] maitu_sum, String[] maitu_rival,
                                            String[] maitu_rate,String[] maitu_starttime,String[] maitu_endtime) {
        List<Map<String,String>> list = new ArrayList<>();
        for (int i=0; i< maitu_starttime.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("indrx", indrx.length > i ? indrx[i] : "");
            map.put("maitu_index", maitu_index.length > i ? maitu_index[i] : "");
            map.put("maitu_sum", maitu_sum.length > i ? maitu_sum[i] : "");
            map.put("maitu_rival", maitu_rival.length > i ? maitu_rival[i] : "");
            map.put("maitu_rate", maitu_rate.length > i ? maitu_rate[i] : "");
            map.put("maitu_starttime", maitu_starttime.length > i ? maitu_starttime[i] : "");
            map.put("maitu_endtime", maitu_endtime.length > i ? maitu_endtime[i] : "");
            list.add(map);
        }
        return list;
    }

    @Test
    public void tst14() {
        String sn = "BBA4LD1808000171";
        int hashCode = Math.abs(sn.hashCode());
        System.err.println(hashCode);
        double limit = ((double)hashCode % 100) / 100;
        System.err.println(limit);
    }

    @Test
    public void test15() {
        try {
            String packageName = this.getClass().getPackage().getName();
            System.err.println("packageName:"+packageName);
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                System.err.println("url:"+url.getPath());
                System.err.println("protocol:"+url.getProtocol());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
