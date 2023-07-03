package org.example.jdk9;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * description: CollectionTest.java
 * date: 2023/06/30
 * author: sunfayun
 * version: 1.0
 */
public class CollectionTest {

    public static void main(String[] args) {
        List.of(1,2,3,4,5).forEach(System.err::println);
        canChangeParam("a", "b", "c");
        Map<String, Integer> map = Map.of("张三", 18, "李四", 20);
        map.entrySet().forEach(System.err::println);
    }

    public static void canChangeParam(String... params) {
        Arrays.stream(params).map(String::toUpperCase).forEach(System.err::println);
    }
}
