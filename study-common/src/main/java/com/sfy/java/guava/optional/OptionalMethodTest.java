package com.sfy.java.guava.optional;

import com.google.common.base.Optional;
import org.junit.Test;

import java.util.Set;

/**
 * Optional功能:使用和避免null
 * @Description: OptionalMethodTest.java
 * @User: sunfayun
 * @Date: 2016/03/16
 * @Version: 1.0
 */
public class OptionalMethodTest {

    @Test
    public void optionalMethodTest(){
        //构建一个Optional对象,传入需要判断的对象,若引用为null则快速失败
        Optional<Integer> integerOptional = Optional.of(5);
        System.err.println("isPresent:"+integerOptional.isPresent());
        if(integerOptional.isPresent()){
            //﻿返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
            System.err.println("integerOptional.get(): "+integerOptional.get());
            //﻿返回Optional所包含的引用，若引用缺失，返回指定的值,相当于指定一个默认值
            System.err.println("integerOptional.or(5):"+integerOptional.or(5));
            //返回Optional所包含的引用，若引用缺失，返回null
            System.err.println("integerOptional.orNull():"+integerOptional.orNull());
        }

        //﻿﻿返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
        Set<Integer> set = integerOptional.asSet();
        System.err.println("integerOptional.asSet():"+set.toString());

        //构建一个Optional对象,传入需要判断的对象,若引用为空,表示缺失,不会立即失败
        Optional<Integer> optional = Optional.fromNullable(5);
        System.err.println("==>"+optional.isPresent());
    }


}
