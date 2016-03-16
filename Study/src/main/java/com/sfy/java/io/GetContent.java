package com.sfy.java.io;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.*;
import java.util.List;
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

}
