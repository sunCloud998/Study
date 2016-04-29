package com.sfy.java.guava.objects;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.sfy.object.Student;
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

    @Test
    public void toStringTest(){
        Student student = new Student();
        student.setName("TEST");
        student.setPassword("test");
        student.setAge(22);
        student.setEmail("1234@163.com");
        student.setNo("10000");

        String targetString = MoreObjects.toStringHelper(student).
                add("name","TEST").
                add("password","test").
                add("age",22).
                add("email","1234@163.com").
                add("no","10000").
                toString();
        System.err.println("toString method:"+targetString);
    }

    @Test
    public void hashTest(){
        Student student = new Student();
        student.setName("TEST");
        student.setPassword("test");
        student.setAge(22);
        student.setEmail("1234@163.com");
        student.setNo("10000");

        int hashCode = Objects.hashCode(student.getName(),student.getPassword(),student.getAge(),student.getEmail(),student.getNo());
        System.err.println("hash Code value is :"+hashCode);
    }

    @Test
    public void compareToTest(){
        Student student = new Student();
        student.setName("TEST");
        student.setPassword("test");
        student.setAge(22);
        student.setEmail("1234@163.com");
        student.setNo("10000");

        int result = student.compareTo(student);
        System.err.println("compareTo result :"+result);
    }
}
