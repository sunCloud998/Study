package com.sfy.other;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

/**
 * @Author: sunfayun
 * @Date: 2015/05/14
 * @Time: 下午12:05
 * Description: StringSplitTest.java
 */
public class StringSplitTest {

    @Test
    public void splitStringTest(){
//        String temp = "idl=6361b8fa-0960-4838-8188-bd100cf6d464_1431575896028,idd=5ab9633e-aa12-4276-adaa-26ac8ee3b8bc_1431575900072";
        String temp = "idd=5ab9633e-aa12-4276-adaa-26ac8ee3b8bc_1431575900072";
//        String temp = "idl=6361b8fa-0960-4838-8188-bd100cf6d464_1431575896028";
//        String temp = null;
        List<String> list = Splitter.on(",").splitToList(temp);
        System.err.println("====>"+list.size());
        Map<String,String> map = Maps.newLinkedHashMap();
        if(CollectionUtils.isNotEmpty(list)){
            for(String string : list){
                List<String> singleList = Splitter.on("=").splitToList(string);
                map.put(singleList.get(0),singleList.get(1));
            }
        }
        System.err.println(map);
    }
//==>[[{"1":"a"}],[{"1":"b"}],[{"3":"c"}],[{"4":"d"}],[{"5":"e"}]]
    @Test
    public void collectionTest(){
        List<HashMap<String,String>> list = Lists.newArrayList();
        HashMap<String,String> map0 = Maps.newHashMap();
        map0.put("groupRoomCode","a");
        list.add(map0);

        HashMap<String,String> map1 = Maps.newHashMap();
        map1.put("groupRoomCode","");
        list.add(map1);

        HashMap<String,String> map2 = Maps.newHashMap();
        map2.put("groupRoomCode","");
        list.add(map2);

        HashMap<String,String> map3 = Maps.newHashMap();
        map3.put("groupRoomCode","a");
        list.add(map3);

        HashMap<String,String> map4 = Maps.newHashMap();
        map4.put("groupRoomCode","e");
        list.add(map4);

        this.separateData(list);
    }

    private List<List<HashMap<String,String>>> separateData(List<HashMap<String,String>> list){
        List<List<HashMap<String,String>>> mapList = new ArrayList<List<HashMap<String, String>>>();
        if(CollectionUtils.isEmpty(list)){
            return mapList;
        }
        Map<String,List<HashMap<String,String>>> stringListMap = new LinkedHashMap<String,List<HashMap<String,String>>>();
        for(int i=0;i<list.size();i++){
            HashMap<String,String> map = list.get(i);
            String groupRoomCodeTemp = map.get("groupRoomCode");
            if(StringUtils.isBlank(groupRoomCodeTemp)){
                List<HashMap<String,String>> hashMapList = new ArrayList<HashMap<String,String>>();
                hashMapList.add(map);
                stringListMap.put(UUID.randomUUID().toString(),hashMapList);
            }else{
                if(null == stringListMap.get(groupRoomCodeTemp)){
                    List<HashMap<String,String>> hashMapList = new ArrayList<HashMap<String,String>>();
                    hashMapList.add(map);
                    stringListMap.put(groupRoomCodeTemp,hashMapList);
                }else {
                    List<HashMap<String,String>> hashMapList = stringListMap.get(groupRoomCodeTemp);
                    hashMapList.add(map);
                    stringListMap.put(groupRoomCodeTemp,hashMapList);
                }
            }
        }
        if(MapUtils.isEmpty(stringListMap)){
            return mapList;
        }
        for(Map.Entry<String,List<HashMap<String,String>>> entry : stringListMap.entrySet()){
            mapList.add(entry.getValue());
        }
        Gson gson = new Gson();
        System.err.println("==>"+gson.toJson(mapList));
        return mapList;
    }

}
