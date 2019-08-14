package com.sfy.java.guava.optional;

import com.google.common.base.Optional;
import com.sfy.exercise.object.Student;
import com.sfy.util.json.JsonMapper;
import org.junit.Test;

/**
 * @Description: OptionalMethodTest.java
 * @Date: 2016/04/29
 * @Author: sunfayun
 * @Version: 1.0
 */
public class OptionalTest {

    @Test
    public void optionalMethodTest(){

        Optional<Student> optional = this.buildStudentInfo(19);

        System.err.println("absent:"+optional.isPresent());

        if(optional.isPresent()){
            //返回 Optional 所包含的引用，若引用缺失，则抛出 java.lang.IllegalStateException
            Student student = optional.get();
            System.err.println("==>"+ JsonMapper.mapString(student));
        }
    }

    /**
     * 测试absent和of方法的使用
     * @param age
     * @return
     */
    public Optional<Student> buildStudentInfo(Integer age){
        Student student = new Student();
        student.setId(001);
        student.setName("TEST");
        student.setAge(age);
        student.setEmail("test@gmail.com");

        if(null == student || null == age || age <= 10){
            return Optional.absent();
        }
        return Optional.of(student);
    }

    @Test
    public void optionalMthodTestTwo(){
        Optional<Student> optional = buildStudentInfoTwo(19);

        //如果 Optional 包含非 null 的引用（引用存在），返回true
        System.err.println("absent value:"+optional.isPresent());

        //返回 Optional 所包含的引用，若引用缺失，返回指定的值
        Student student = optional.or(new Student(002,"Name",22,"email@gmail.com"));
        //返回 Optional 所包含的引用，若引用缺失，返回 null
        //Student student = optional.orNull();

        System.err.println("Student Info:"+JsonMapper.mapString(student));
    }

    public Optional<Student> buildStudentInfoTwo(Integer age){
        Student student = new Student();
        student.setId(001);
        student.setName("TEST");
        student.setAge(age);
        student.setEmail("test@gmail.com");

        if(null == student || null == age || age <= 10){
            return Optional.fromNullable(null);
        }
        //创建指定引用的 Optional 实例，若引用为 null 则表示缺失
        return Optional.fromNullable(student);
    }

}
