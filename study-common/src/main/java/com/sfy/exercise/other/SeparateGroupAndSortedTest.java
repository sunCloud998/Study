package com.sfy.exercise.other;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Function:
 * @Description: SeparateGroupAndSortedTest.java
 * @Date: 2016/07/06
 * @Author: sunfayun
 * @Version: 1.0
 */
public class SeparateGroupAndSortedTest {

    @Test
    public void getSortedDataTest(){
        List<Map<String,String>> list = this.getSortedTeamInfoEveryGroup(this.initData());
        for(Map<String,String> map : list){
            System.err.println(map.toString());
        }
    }

    /**
     * 分组
     * @param teamVisaList
     * @return
     */
    public Map<String,List<Map<String,String>>> separateTeamVisaInfo(List<Map<String,String>> teamVisaList){
        if(CollectionUtils.isEmpty(teamVisaList)){
            return null;
        }
        Map<String,List<Map<String,String>>> map = new HashMap<String, List<Map<String, String>>>();
        for(Map<String,String> teamMap : teamVisaList){
            String produceCode = teamMap.get("produceCode");
            if(map.containsKey(produceCode)){
                List<Map<String,String>> list = map.get(produceCode);
                list.add(teamMap);
            }else {
                List<Map<String,String>> list = new ArrayList<Map<String, String>>();
                list.add(teamMap);
                map.put(produceCode,list);
            }
        }
        return map;
    }

    /**
     * 每组获取最大团期并排序
     * @param groupMap
     * @return
     */
    public Map<String,String> getMaxDateStartEveryGroupAndSorted(Map<String,List<Map<String,String>>> groupMap){
        if(MapUtils.isEmpty(groupMap)){
            return null;
        }

        Map<String,String> map = new HashMap<>();
        for(Map.Entry<String,List<Map<String,String>>> entry : groupMap.entrySet()){
            List<Map<String,String>> list = entry.getValue();
            list = this.sortVisaTotalCountInfo(list);
            map.put(entry.getKey(),list.get(0).get("dateStart"));
        }
        List<Map.Entry<String,String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                try {
                    Date $dateStart = stringToDate(o1.getValue());
                    Date _dateStart = stringToDate(o2.getValue());
                    return String.valueOf($dateStart.getTime()).compareTo(String.valueOf(_dateStart.getTime()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        Map<String,String> sortedMap = new LinkedHashMap<>();//创建有序的map
        for(Map.Entry<String,String> entry : list){
            sortedMap.put(entry.getKey(),entry.getValue());
        }
        return sortedMap;
    }

    /**
     * 获取排序后的结果
     * @param teamVisaList
     * @return
     */
    public List<Map<String,String>> getSortedTeamInfoEveryGroup(List<Map<String,String>> teamVisaList){
        if(CollectionUtils.isEmpty(teamVisaList)){
            return null;
        }
        List<Map<String,String>> list = new ArrayList<Map<String, String>>();
        //获取分组数据
        Map<String,List<Map<String,String>>> groupMap = this.separateTeamVisaInfo(teamVisaList);
        //获取每组最大团期的排序结果
        Map<String,String> sortedMaxDateStartEveryGroupMap = this.getMaxDateStartEveryGroupAndSorted(groupMap);
        for(Map.Entry<String,String> entry : sortedMaxDateStartEveryGroupMap.entrySet()){
            list.addAll(groupMap.get(entry.getKey()));
        }
        return list;
    }

    /**
     * 按出团日期排序
     * @param teamVisaList
     * @return
     */
    public List<Map<String,String>> sortVisaTotalCountInfo(List<Map<String, String>> teamVisaList){
        if(CollectionUtils.isEmpty(teamVisaList)){
            return null;
        }
        try {
            for (int i = 0; i < teamVisaList.size(); i++) {
                for (int j=i;j>0;j--){
                    Date $dateStart = this.stringToDate(teamVisaList.get(j).get("dateStart"));
                    Date _dateStart = this.stringToDate(teamVisaList.get(j-1).get("dateStart"));
                    if($dateStart.getTime() < _dateStart.getTime()){
                        Map<String,String> map = teamVisaList.get(j);
                        teamVisaList.set(j,teamVisaList.get(j-1));
                        teamVisaList.set(j-1,map);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamVisaList;
    }

    /**
     * @name:转换日期
     * @Desprition: TODO
     * @Date: 2016-6-20 下午4:54:18
     * @Author: wenjiao
     */
    public Date stringToDate(String source) throws Exception{
        if(StringUtils.isBlank(source)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Map<String,String>> initData(){
        List<Map<String,String>> list = Lists.newArrayList();
        list.add(ImmutableMap.<String, String>of("produceCode","1","dateStart","2016-06-07"));
        list.add(ImmutableMap.<String, String>of("produceCode","1","dateStart","2016-06-08"));
        list.add(ImmutableMap.<String, String>of("produceCode","1","dateStart","2016-06-09"));
        list.add(ImmutableMap.<String, String>of("produceCode","1","dateStart","2016-06-07"));
        list.add(ImmutableMap.<String, String>of("produceCode","2","dateStart","2016-06-07"));
        list.add(ImmutableMap.<String, String>of("produceCode","2","dateStart","2016-06-05"));
        list.add(ImmutableMap.<String, String>of("produceCode","3","dateStart","2016-06-07"));
        list.add(ImmutableMap.<String, String>of("produceCode","3","dateStart","2016-06-08"));
        return list;
    }

    @Test
    public void test02(){
        System.err.println(Math.abs("mt001035".hashCode()) % 10000);
    }

    @Test
    public void test03() {
        List<String> targetList = Lists.newArrayList();
        List<String> list = ImmutableList.of("mt001011","mt001010");
        int rate = 4203;
        int status = 1;// on
        if(status == 1) {
            for (String pushToken : list) {
                int hashCode = Math.abs(pushToken.hashCode());
                int sourceRate = hashCode % 10000;
                System.err.println(pushToken + " hash code:" + sourceRate);
                if(sourceRate < rate) {
                    targetList.add(pushToken);
                }
            }
            System.err.println(targetList);;
        } else {
            // 通道状态为关，按比例过滤sn
            for (String pushToken : list) {
                int hashCode = Math.abs(pushToken.hashCode());
                int sourceRate = hashCode % 10000;
                System.err.println(pushToken + " hash code:" + sourceRate);
                if(sourceRate >= rate) {
                    targetList.add(pushToken);
                }
            }
            System.err.println(targetList);
        }
    }

}
