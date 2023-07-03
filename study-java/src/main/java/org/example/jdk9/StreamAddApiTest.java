package org.example.jdk9;

import java.util.List;

/**
 * description:
 * date: 2023/07/03
 * author: sunfayun
 * version: 1.0
 */
public class StreamAddApiTest {

    public static void main(String[] args) {
        takeWhileTest();
        dropWhileTest();
    }

    public static void dropWhileTest() {
        List<Integer> list = List.of(45,43,76,87,42,77,90,73,67,88);
        // 返回第一个不满足条件之后的所有元素
        list.stream().dropWhile(x -> x < 80).forEach(System.err::println);
    }

    public static void takeWhileTest() {
        List<Integer> list = List.of(45,43,76,87,42,77,90,73,67,88);
        // 从第一个元素开始匹配，直到第一个不满足条件的为止，返回前面的数据
        list.stream().takeWhile(x -> x < 80).forEach(System.err::println);

        List<Integer> sortList = List.of(1,2,3,4,5,6);
        sortList.stream().takeWhile(x -> x < 5).forEach(System.err::println);
        System.err.println("==============================================");
    }

}
