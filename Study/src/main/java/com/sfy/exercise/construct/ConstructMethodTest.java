package com.sfy.exercise.construct;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @Function:构造方法测试
 * @Description: ConstructMethodTest.java
 * @Date: 2016/06/29
 * @Author: sunfayun
 * @Version: 1.0
 */
@Data
public class ConstructMethodTest implements InitializingBean {

    private String name;
    private String password;

    {
        System.err.println("默认的构造方法开始执行。。。。。");
    }

    static {
        System.err.println("static方法开始执行。。。。。");
    }

    public ConstructMethodTest(){
        System.err.println("空的构造方法开始执行。。。。");
    }

    public ConstructMethodTest(String name,String password){
        this.name = name;
        this.password = password;
        System.err.println("包含参数的构造方法开始执行。。。。。");
    }

    @PostConstruct
    public void initData(){
        name = "Test";
        password = "Password";
        System.err.println("使用@PostConstruct注解的方法开始执行。。。。");
    }

    public static void main(String[] args) {
        System.err.println("Main方法开始执行。。。。");
        new ConstructMethodTest();
//        new ConstructMethodTest("HaHa","NB");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("afterPropertiesSet....");
    }
}
