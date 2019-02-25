package com.sfy.java.io;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: GetContent.java
 * @Author: sunfayun
 * @Date: 2015/10/20
 * @Time: 上午11:16
 * @Version 1.0
 */
public class GetContent {

    public static void main(String[] args) throws IOException {
        InputStream is = GetContent.class.getClassLoader().getResourceAsStream("product/tuan.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line="";
        Set<String> tuanSet = Sets.newHashSet();
        while ((line = br.readLine()) != null){
            String[] lineArr = line.split(",");
            for(String str : lineArr){
                tuanSet.add(str);
            }
        }


        is = GetContent.class.getClassLoader().getResourceAsStream("product/tts.txt");
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        String ttsLine="";
        Set<String> ttsSet = Sets.newHashSet();
        while ((ttsLine = br.readLine()) != null){
            String[] lineArr = ttsLine.split(",");
            for(String str : lineArr){
                ttsSet.add(str);
            }
        }
        System.err.println("TTS Size:"+ttsSet.size());
        System.err.println("Tuan Size:"+tuanSet.size());

        List<String> list = Lists.newArrayList();
        for(String data : ttsSet){
            if(tuanSet.contains(data)){
                list.add(data);
            }
        }

        for(String s : list){
            System.err.println(s);
        }


        is = GetContent.class.getClassLoader().getResourceAsStream("test.txt");
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        String kdLine="";
        StringBuilder sb = new StringBuilder();
        int x = 0;
        while ((kdLine = br.readLine()) != null){
            x++;
            sb.append(kdLine).append("|");
        }
        System.err.println("===============>"+x);
        System.err.println("======>"+sb.toString());

    }


    @Test
    public void test(){
        try {
            InputStream is = GetContent.class.getClassLoader().getResourceAsStream("test.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            List<String> list = Lists.newArrayList();
            String line;
            while ((line = reader.readLine()) != null){
                Integer id = Integer.valueOf(line.substring(0,line.indexOf("l"))) % 16;
                //String sql = "update user_order"+id+" set biz_type = 1 where id = '"+line+"' and biz_type = 0;";
                String sql = "select id,trade_id,third_trade_id from pay_order"+id+" where user_order_id = '"+line+"' and stat = 3 UNION ALL ";
                list.add(sql);
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("123.sql")));
            for(String str : list){
                writer.write(str);
                writer.flush();
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01(){
        try {
            InputStream is = GetContent.class.getClassLoader().getResourceAsStream("test.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Map<String,List<String>> map = Maps.newHashMap();
            String line;
            while ((line = reader.readLine()) != null){
                List<String> list = Splitter.on("\t").splitToList(line);
                if(map.containsKey(list.get(0))){
                    List<String> nameList = map.get(list.get(0));
                    if(!nameList.contains(list.get(1))){
                        nameList.add(list.get(1));
                    }
                }else {
                    List<String> nameList = Lists.newArrayList();
                    nameList.add(list.get(1));
                    map.put(list.get(0),nameList);
                }
            }

            for(Map.Entry<String,List<String>> entry : map.entrySet()){
                if(entry.getValue().size() > 1){
                    System.err.println("poiId:"+entry.getKey() + " name:"+entry.getValue());
                }
            }

//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("123.sql")));
//            for(String str : list){
//                writer.write(str);
//                writer.flush();
//                writer.newLine();
//            }
//            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01_sum(){
        try {
            InputStream is = GetContent.class.getClassLoader().getResourceAsStream("test01.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            List<Integer> list = Lists.newArrayList();
            String line;
            while ((line = reader.readLine()) != null){
                List<String> strList = Splitter.on("\t").splitToList(line);
                list.add(Integer.parseInt(strList.get(1)) / 100);
            }

            int sum = 0;
            for (int s : list){
                sum = sum + s;
            }
            System.err.println("==>"+sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test02(){
        try {
            InputStream is = GetContent.class.getClassLoader().getResourceAsStream("2.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            List<String> key = Lists.newArrayList();
            List<String> value = Lists.newArrayList();
            String line;
            while ((line = reader.readLine()) != null){
                List<String> splitList = Splitter.on(",").splitToList(line);
                key.add(splitList.get(0));
                value.add(splitList.get(1));
            }
            String poiids = Joiner.on(",").join(key);
            String deviceNo = "'";
            for(String str : value) {
                deviceNo = deviceNo + str + "','";
            }
            String sql = "select poi_id,device_no,count(1) from pp_order where poi_id in ("+poiids+") and device_no in ("+deviceNo+") group by poi_id,device_no";
            System.err.println(""+sql);

//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("123.sql")));
//            for(String str : list){
//                writer.write(str);
//                writer.flush();
//                writer.newLine();
//            }
//            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03() {
        try {
            InputStream is = GetContent.class.getClassLoader().getResourceAsStream("123");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            List<String> list = Lists.newArrayList();
            String line;
            while ((line = reader.readLine()) != null){
                String temp = line.substring(0,5);
                if(list.contains(temp)) {
                    continue;
                }
                list.add(temp);
            }
            System.err.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
