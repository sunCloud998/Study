package org.example.jdk10;

import java.util.List;

/**
 * description:
 * date: 2023/07/03
 * author: sunfayun
 * version: 1.0
 */
public class VarTest {

    public static void main(String[] args) {
        var list = List.of(1,2,3,4,5);
        list.forEach(System.err::println);
        System.err.println("================================");
        for (var i : list) {
            System.err.println(i);
        }
    }

}
