package org.example.jdk8;

import java.lang.annotation.Repeatable;

/**
 * @function: 重复注解使用
 * @description: RepeatAnnotationTest.java
 * @date: 2023/06/30
 * @author: sunfayun
 * @version: 1.0
 */
public class RepeatAnnotationTest {

    @Repeatable(Authorities.class)
    public @interface Authority {
        String role();
    }

    public @interface Authorities {
        Authority[] value();
    }

    @Authority(role = "hello")
    @Authority(role = "world")
    public static void doSomething() {
        System.err.println("重复注解使用");
    }

    public static void main(String[] args) {
        doSomething();
    }

}
