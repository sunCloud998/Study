package com.sfy.object;

import com.google.common.collect.ComparisonChain;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: Student.java
 * @User: sunfayun
 * @Date: 2016/03/16
 * @Version: 1.0
 */
@Data
public class Student implements Serializable {

    private String name;
    private String password;
    private Integer age;
    private String email;
    private String no;

    public int compareTo(Student student){
        return ComparisonChain.start().
                compare(this.name,student.name).
                compare(this.password,student.password).
                compare(this.age,student.age).
                compare(this.email,student.email).
                compare(this.no,student.no).
                result();
    }

}
