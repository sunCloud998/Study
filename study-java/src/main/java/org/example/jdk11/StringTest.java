package org.example.jdk11;

/**
 * description:
 * date: 2023/07/03
 * author: sunfayun
 * version: 1.0
 */
public class StringTest {

    public static void main(String[] args) {
        // 判断字符串是否为空白字符串
        System.err.println("".isBlank());
        // 去除首尾空格
        System.err.println(" hello world ".strip());
        // 去除尾部空格
        System.err.println(" hello world ".stripTrailing());
        // 去除首部空格
        System.err.println(" hello world ".stripLeading());
        // 复制字符串
        System.err.println("java".repeat(3));
        // 行统计
        System.err.println("A\nB\nC\n".lines().count());
    }

}
