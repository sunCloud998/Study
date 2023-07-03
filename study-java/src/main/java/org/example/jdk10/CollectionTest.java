package org.example.jdk10;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * date: 2023/07/03
 * author: sunfayun
 * version: 1.0
 */
public class CollectionTest {

    public static void main(String[] args) {
        var list = List.of(1,2,3,4,5);
        // copyOf会判断参数集合是否是不可变的，如果是直接返回，如果不是会调用of创建一个不可变集合返回
        var listCopy = List.copyOf(list);
        System.err.println(list == listCopy);

        var changeList = new ArrayList<Integer>();
        // 传入的集合是可变的，所以copyOf会调用of重新创建一个不可变集合，返回的是一个新的对象
        var changeListCopy = List.copyOf(changeList);
        System.err.println(changeListCopy == changeList);
    }

}
