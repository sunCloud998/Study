package com.sfy.other;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * @Description: ModTest.java
 * @Author: sunfayun
 * @Date: 2015/07/22
 * @Time: 上午1:34
 * @Version 1.0
 */
public class ModTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        int x = 21;
//        int y =20;
//        System.err.println("取摸："+x % y);
//        System.err.println("除法："+x / y);

//        String str = "null";
//        if(StringUtils.isBlank(str) || str.equalsIgnoreCase("null")){
//            System.err.println("haha");
//        }
//        System.err.println(str.substring(0,str.indexOf(" ")));//error

//        String test = "测试测试测试测试.xls";
//        String aa = new String(test.getBytes("GBK"), "iso8859-1");
//        System.err.println("=====>"+aa);

        List<String> list1 = Lists.newArrayList();
        list1.add("1");
        list1.add("1");
        list1.add("1");
        list1.add("1");
        list1.add("1");
        list1.add("1");

        List<String> list2 = Lists.newArrayList();
        list2.add("2");
        list2.add("2");
        list2.add("2");
        list2.add("2");


        List<String> list3 = Lists.newArrayList();
        list3.add("3");
        list3.add("3");
        list3.add("3");
        list3.add("3");
        list3.add("3");
        list3.add("3");
        list3.add("3");
        list3.add("3");
        list3.add("3");
        list3.add("3");
        list3.add("3");

        List<List<String>> list = Lists.newArrayList();
        list.add(list1);
        list.add(list2);
        list.add(list3);

       List<Integer> sizeList = Lists.newArrayList();
        for(List<String> stringList : list){
            sizeList.add(stringList.size());
        }
        Collections.sort(sizeList);
        Collections.reverse(sizeList);
        System.err.println(sizeList);

        List<List<String>> targetList = new ArrayList<List<String>>();
        for(Integer size : sizeList){
            for(List<String> newList : list){
                if(size == newList.size()){
                    targetList.add(newList);
                }
            }
        }
        System.err.println(targetList);
    }

    @Test
    public void rangeTest(){
//        IntStream.range(0,10).forEach(index ->{
//            System.err.println("==>"+index);
//        });

        IntStream.range(0,10).filter(value -> value>5).forEach(index -> System.err.println("=>"+index));
    }

    @Test
    public void encodeTest(){
        String str = "http://l-dumper2.vc.dev.cn0.qunar.com:8690/qchat/effective_comm?customer=zhu886&user=zxfedlm6815&start_time=2017-05-16 16:30:11&dur_time=15";
        try {
            System.err.println("==>"+ URLEncoder.encode("2017-05-16 16:30:11", Charsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
