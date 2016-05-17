package com.sfy.other;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Service;
import com.sun.scenario.effect.MotionBlur;
import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.impl.jam.internal.elements.SourcePositionImpl;
import org.junit.Test;
import sun.org.mozilla.javascript.internal.ErrorReporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: ArrayListTest.java
 * @Date: 2016/01/08
 * @Author: sunfayun
 * @Version: 1.0
 */
public class ArrayListTest {

    @Test
    public void removeTest(){
        List<String> list = Lists.newArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        list.add("gg");
        list.add("hh");
        list.add("ii");
        list.add("jj");
        list.add("kk");
        list.add("ll");
        list.add("mm");
        list.add("nn");
        list.add("oo");
        list.add("pp");
        list.add("qq");
        list.add("rr");
        list.add("ss");
        list.add("tt");
        list.add("uu");
        list.add("vv");
        list.add("ww");

        System.err.println("原来集合大小："+list.size());
        System.err.println("原来集合内容："+list);
        int x = list.size() % 3 == 0 ? list.size()/3 : list.size()/3+1;
        for(int i=1;i<=x;i++){
            int t = 3*(i-1);
//            if(i==x){
//                t = list.size() - (3*(i-1));
//            }
            List<String> newList = this.deleteMapNode(list,t);
            System.err.println("=====>"+newList);
        }
        System.err.println("删除后集合的大小："+list.size());
        System.err.println("删除后集合的内容："+list);
    }

    private List<String> deleteMapNode(List<String> list, int index) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<String> tempList = new ArrayList<>();
        for (int i = index; i < list.size(); i++) {
            tempList.add(list.get(i));
        }
        return tempList;
    }

    @Test
    public void insertSqlTest(){
        String mobile="13888906104,15810577521,15876540448,13825059818,13810168143,15989274383,";
        mobile +="18666111852,18688860092,15805811265,18011464959,13340831913,15754971023,17721080366,13819139671,18502271683,18317133176,15755185148,13603721383,15813330057,18053236682,13784977852,13916145725,18846077661,18208431137,18208431137,13802430391,15802313801,18618417329,18645015599,13911686491,18670385072,13761226604,13534782038,15624952966,18740460507,15816076454,18622960855,13818774569,13125538732,13801969563,13808854913,13801969563,13801969563,15889738543,15352602256,13037885881,18910433883,15600000084,18825302690,18160030001,13718881828,18810600240,13543439220,13564551253,18628360916,13637904809,13512771067,15166038980,13438997828,13801969563,13751066766,15166038980,18110917744,18608241007,13790809796,13757983444,15711207069,13808121602,13808121602,13808121602,13846510999,13881926799,18677039612,18689727297,13306019293,13662325002,13662325002,15870028991,18113945169,15828529547,18825025243,18990563337,13735368714,18009319061,13534593443,13202829668,15665000756,13916165520,18201014087,15810656022,15988891850,18709711690,15615553125,13610334620,18245477499,13564720145,13925457940,13158506063,13507993856,13830293256,15308061970,15549863000,18167122593,15086856008,15606992979,15108412312,13637515331,15540889288,13876081519,18823423989";
        List<String> list = Splitter.on(",").splitToList(mobile);
        String content="感谢参与“有红牛自在游”去哪儿519疯游节!送您红牛官方旗舰店10元优惠券,数量有限快抢http://qnr.io/vdhtkp";
        for(String str : list){
            String sql = "insert into b2c_sms_queue (number, content, account, group_id) values ('"+str+"','"+content+"','qunar_vacation','vacation');";
            System.err.println(sql);
        }
    }

    @Test
    public void methodTest(){
        int count = 199/200;
        System.err.println("count="+count);
    }

    @Test
    public void splitterListTest(){
        List<String> list = Lists.newArrayList();
        for(int i=0;i<49;i++){
            list.add(i+"");
        }
        List<List<String>> targetList = this.splitterSourceList(list,11);
        for(List<String> stringList : targetList){
            System.err.println(stringList);
        }
    }

    private List<List<String>> splitterSourceList(List<String> sourceList,Integer PAGE_SIZE){
        if(CollectionUtils.isEmpty(sourceList)){
            return null;
        }
        List<List<String>> targetList = Lists.newArrayList();
        if(sourceList.size() < PAGE_SIZE){
            targetList.add(sourceList);
            return targetList;
        }
        int pageCount = (sourceList.size() + PAGE_SIZE - 1) /PAGE_SIZE;
        for(int i=0;i<pageCount;i++){
            int currentRow = i * PAGE_SIZE;
            if(i == pageCount -1){
                List<String> lastList = Lists.newArrayList();
                for(int j=currentRow;j<sourceList.size();j++){
                    lastList.add(sourceList.get(j));
                }
                targetList.add(lastList);
            }else {
                List<String> list = Lists.newArrayList();
                for(int j=currentRow;j<PAGE_SIZE + currentRow;j++){
                    list.add(sourceList.get(j));
                }
                targetList.add(list);
            }
        }
        return targetList;
    }

}
