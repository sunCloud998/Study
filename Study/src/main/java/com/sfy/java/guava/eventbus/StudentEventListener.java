package com.sfy.java.guava.eventbus;

import com.google.common.eventbus.Subscribe;
import com.sfy.object.Student;
import com.sfy.util.json.JsonMapper;

/**
 * 事件监听者
 * @Description: StudentEventListener.java
 * @Date: 2016/04/29
 * @Author: sunfayun
 * @Version: 1.0
 */
public class StudentEventListener {

    @Subscribe
    public void handelEvent(Student student){
        if(null == student){
            System.err.println("接收到的对象为空");
        }else {
            System.err.println("Student Info :"+ JsonMapper.mapString(student));
        }
    }

    @Subscribe
    public void handelOtherEvent(Student student){
        System.err.println("Other Student Info："+JsonMapper.mapString(student));
    }

}
