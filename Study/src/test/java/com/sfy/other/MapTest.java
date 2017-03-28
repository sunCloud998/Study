package com.sfy.other;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: MapTest.java
 * @User: sunfayun
 * @Date: 2017/03/23
 * @Version: 1.0
 */
public class MapTest {

    @Test
    public void concurrentHashMapTest(){
        Map<String,String> map = new ConcurrentHashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        map.put("4","d");
        map.put("5","e");
        map.put("6","f");
    }

}
