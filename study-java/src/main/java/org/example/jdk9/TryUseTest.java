package org.example.jdk9;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * description:
 * date: 2023/07/03
 * author: sunfayun
 * version: 1.0
 */
public class TryUseTest {

    /**
     * 使用jdk8的方式自动关闭资源
     */
    public static void fileRead8() {
        try (
                FileInputStream fis = new FileInputStream("D:\\个人资料\\学习资料\\Java文档\\学习代码\\Study\\study-java\\src\\main\\java\\org\\example\\jdk9\\CollectionTest.java");
                BufferedInputStream bis = new BufferedInputStream(fis);
        ) {
            System.err.println(new String(bis.readAllBytes(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void fileRead9() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("D:\\个人资料\\学习资料\\Java文档\\学习代码\\Study\\study-java\\src\\main\\java\\org\\example\\jdk9\\CollectionTest.java");
        BufferedInputStream bis = new BufferedInputStream(fis);
        try(fis; bis) {
            System.err.println(new String(bis.readAllBytes(), Charset.defaultCharset()));
        }catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        fileRead8();
        fileRead9();
    }
}
