package com.sfy.java.guava.ordering;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.sfy.object.Student;
import org.junit.Test;
import org.springframework.core.annotation.Order;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 排序器
 * @Description: OrderingTest.java
 * @Date: 2016/03/16
 * @Author: sunfayun
 * @Version: 1.0
 */
public class OrderingTest {

    /**
     * 说明：new Ordering创建一个比较器，在比较器的内部实现方法中实现自定义的排序规则
     * Ordering的泛型为需要排序的对象，定义完成后，适用调用Ordering对象的相应方法，完成
     * 对泛型里对象的排序
     */
    @Test
    public void orderingTest(){
        List<Student> studentList = this.buildStudentDataInfo();

        /**
         * 自定义的比较器的方式
         */
        Ordering<Student> ordering = new Ordering<Student>() {
            @Override
            public int compare(@Nullable Student student, @Nullable Student t1) {
                /**升序排列*/
                return student.getAge() - t1.getAge();
            }
        };
        studentList = ordering.immutableSortedCopy(studentList);
        System.err.println("适用两个年龄相减的结果："+studentList.toString());

        /**
         * 使用比较器的方式来比较
         */
        Ordering<Student> orderingCompator = new Ordering<Student>() {
            @Override
            public int compare(@Nullable Student student, @Nullable Student t1) {
                return student.compareTo(t1);
            }
        };
        studentList = orderingCompator.immutableSortedCopy(studentList);
        System.err.println("适用比较器compareTo方法的结果："+studentList.toString());

    }

    /**
     * 测试Ordering的其他方法：
     */
    @Test
    public void orderingOtherMethodTest(){
        List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");
        System.err.println("原始字符串顺序："+list);

        Ordering<String> naturalOrdering = Ordering.natural();
        System.err.println("按照自然顺序排列："+naturalOrdering.sortedCopy(list));

        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        System.err.println("适用toString返回字符串，按照字典顺序进行排序："+usingToStringOrdering.sortedCopy(list));

        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();
        System.err.println("返回所有对象的任意顺序："+arbitraryOrdering.sortedCopy(list));

        System.err.println("按照自然顺序的反序输出："+naturalOrdering.reverse().sortedCopy(list));


    }

    private List<Student> buildStudentDataInfo(){
        List<Student> studentList = Lists.newArrayList();

        Student student = new Student();
        student.setName("Test");
        student.setAge(26);
        student.setPassword("hello");
        student.setEmail("abc@163.com");
        student.setNo("0001");
        studentList.add(student);

        student = new Student();
        student.setName("Test01");
        student.setAge(22);
        student.setPassword("hello01");
        student.setEmail("abcd@163.com");
        student.setNo("0002");
        studentList.add(student);

        student = new Student();
        student.setName("Test02");
        student.setAge(24);
        student.setPassword("hello02");
        student.setEmail("abce@163.com");
        student.setNo("0003");
        studentList.add(student);

        return studentList;
    }

}
