package com.sfy.java.guava;

import com.google.common.base.Objects;
import org.junit.Test;

/**
 * 测试Guava中的Objects类
 * @author sunfayun
 * @time 2015/04/15 16:57
 */
public class ObjectsTest {

    @Test
    public void equalTest(){
        System.err.println("==>"+ Objects.equal("a","a"));
        System.err.println("==>"+Objects.equal(null,"a"));
        System.err.println("==>"+Objects.equal("a",null));
        System.err.println("==>"+Objects.equal(null,null));
    }

}
