package com.sfy.java.guava.file;

import org.junit.Test;

import java.io.File;

/**
 * @function:
 * @description: FileTest.java
 * @date: 2019/04/30
 * @author: sunfayun
 * @version: 1.0
 */
public class FileTest {

    @Test
    public void testFileIsExit() {
        File file = new File("/opt/bmp");
        if(!file.exists()) {
            boolean result = file.mkdir();
            System.err.println("=>"+result);
        }
    }

}
