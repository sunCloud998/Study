package com.sfy.other;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: ModTest.java
 * @Author: sunfayun
 * @Date: 2015/07/22
 * @Time: 上午1:34
 * @Version 1.0
 */
public class ModTest {

    public static void main(String[] args) {
        int x = 21;
        int y =20;
        System.err.println("取摸："+x % y);
        System.err.println("除法："+x / y);

        String str = "null";
        if(StringUtils.isBlank(str) || str.equalsIgnoreCase("null")){
            System.err.println("haha");
        }
        System.err.println(str.substring(0,str.indexOf(" ")));//error
    }

}
