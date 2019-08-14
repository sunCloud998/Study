package com.sfy.java.guava.str;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @function:
 * @description: StringTest.java
 * @date: 2018/01/22
 * @author: sunfayun
 * @version: 1.0
 */
public class StringTest {

    @Test
    public void test01(){
        String testStr = " abc ";
        String temp = CharMatcher.WHITESPACE.trimFrom(testStr);
        if(temp.equals("abc")) {
            System.err.println("==>成功");
        }else {
            System.err.println("===>失败");
        }
    }

    @Test
    public void test02() {
        String x = "'  or  '%'=='";
        String target = dealEscapParameter(x);
        System.err.println(target);
    }

    public static String dealEscapParameter(String param){
        if(param == null || param.equals("")) {
            return "";
        }
        StringBuffer buf = new StringBuffer((int) (param.length() * 1.1));
        int stringLength = param.length();

        for (int i = 0; i < stringLength; ++i) {
            char c = param.charAt(i);
            switch (c) {
                case '\'':
                    buf.append('\'');
                    buf.append('\'');
                    break;
                default:
                    buf.append(c);
            }
        }
        return buf.toString();
    }

}
