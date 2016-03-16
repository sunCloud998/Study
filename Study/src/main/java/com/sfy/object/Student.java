package com.sfy.object;

import com.google.common.collect.ComparisonChain;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: Student.java
 * @User: sunfayun
 * @Date: 2016/03/16
 * @Version: 1.0
 */
@Data   @ToString
public class Student implements Serializable {

    private String name;
    private String password;
    private Integer age;
    private String email;
    private String no;

    /**
     * 比较student的全部属性信息
     * @param student
     * @return
     */
    public int compareToAll(Student student){
        return ComparisonChain.start().
                compare(this.name,student.name).
                compare(this.password,student.password).
                compare(this.age,student.age).
                compare(this.email,student.email).
                compare(this.no,student.no).
                result();
    }

    public int compareTo(Student student){
        //按照年龄升序排列
        //return ComparisonChain.start().compare(this.age,student.getAge()).result();
        //按照年龄降序排列
        return ComparisonChain.start().compare(student.getAge(),this.age).result();
    }

}
