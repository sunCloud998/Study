package org.example.jdk8;

import java.util.Optional;

/**
 * @function:
 * @description: OptionalTest.java
 * @date: 2023/06/30
 * @author: sunfayun
 * @version: 1.0
 */
public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("zhangsan");
        Optional<String> empty = Optional.empty();
        // 判断是否存在
        if(name.isPresent()) {
            System.err.println(name.get());
        }
        name.ifPresent(value -> System.err.println("ifPresent:" + value));
        empty.ifPresent(value -> System.err.println("ifPresent empty:" + value));
        System.err.println(empty.orElse("orElse为空的情况"));
        System.err.println(name.orElse("orElse不为空的情况"));
        System.err.println(name.orElseGet(() -> "name.orElseGet hello world"));
        System.err.println(empty.orElseGet(() -> "empty.orElseGet hello world"));

        Optional<String> nameToUpper = name.map(String::toUpperCase);
        System.err.println("name.map:" + nameToUpper.get());

        nameToUpper = name.flatMap(value -> Optional.of(value.toUpperCase()));
        System.err.println("name.flatMap:" + nameToUpper.get());
    }

}
