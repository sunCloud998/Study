package com.sfy.java.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.sfy.exercise.object.Student;

import java.util.concurrent.Executors;

/**
 * @Description: StudentEventProvider.java
 * @Date: 2016/04/29
 * @Author: sunfayun
 * @Version: 1.0
 */
public class StudentEventProvider {

    public static void main(String[] args) {
        //创建同步事件总线
        //EventBus eventBus = new EventBus("student event bus");
        //创建异步事件总线
        EventBus asyncEventBus = new AsyncEventBus("asyncEventBus", Executors.newCachedThreadPool());

        StudentEventListener eventListener = new StudentEventListener();
        //eventBus.register(eventListener);

        //注册事件监听
        asyncEventBus.register(eventListener);

        //构造消息数据
        Student student = new Student(001,"TEST",22,"test@gmail.com");
        //eventBus.post(student);

        asyncEventBus.post(student);
    }

}
