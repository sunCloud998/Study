package org.example.jdk9;

/**
 * description:
 * date: 2023/07/03
 * author: sunfayun
 * version: 1.0
 */
public class PrivateMethodInterfaceTest implements PrivateMethodInterface {
    @Override
    public void normalInterfaceMethod() {
        System.err.println("接口方法");
    }

    public static void main(String[] args) {
        PrivateMethodInterfaceTest dit = new PrivateMethodInterfaceTest();
        dit.defaultMethod();
        dit.normalInterfaceMethod();
//        dit.init(); // error
    }
}
