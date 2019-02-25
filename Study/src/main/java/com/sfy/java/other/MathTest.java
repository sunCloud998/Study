package com.sfy.java.other;

import org.junit.Test;

/**
 * @Function: 计算相关的测试类
 * @Description: MathTest.java
 * @Date: 2017/07/31
 * @Author: sunfayun
 * @Version: 1.0
 */
public class MathTest {

    @Test
    public void test01(){
        for(int i=0;i<16;i++){
            System.err.print(i % 16);
        }
        System.err.println("==>"+Integer.MAX_VALUE);
    }

    @Test
    public void test02(){
        int temp = 1950;
        double value = (double) temp / 100;
        System.err.println(value);
    }
}
