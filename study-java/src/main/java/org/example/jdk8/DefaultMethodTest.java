package org.example.jdk8;

/**
 * @function:
 * @description: DefaultMethodTest.java
 * @date: 2023/06/30
 * @author: sunfayun
 * @version: 1.0
 */
public class DefaultMethodTest {

    public interface A {
        default void aa() {
            System.err.println("接口A的默认实现方法");
        }
    }

    public interface B {
        default void aa() {
            System.err.println("接口B的默认方法实现");
        }
    }

    public static class AB implements A, B {

        @Override
        public void aa() {
            System.err.println("子类实现的方法");
        }
    }

    public interface C extends A, B {

        @Override
        default void aa() {
            System.err.println("继承的接口实现的默认方法");
        }
    }

    public static class CC implements A,B,C {

    }

    public static void main(String[] args) {
//        new AB().aa();
        new CC().aa();
    }

}
