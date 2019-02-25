package com.sfy.java.guava.cache;

import com.google.common.base.Joiner;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @function:
 * @description: AutoLoadCacheTest.java
 * @date: 2018/03/19
 * @author: sunfayun
 * @version: 1.0
 */
public class AutoLoadCacheTest {

    static int i = 1;
    List<String> list = Lists.newArrayList();

    @Test
    public void autoLoadTest() {
        LoadingCache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .refreshAfterWrite(5, TimeUnit.SECONDS)
                .expireAfterWrite(15,TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.err.println("---第：" + (i++) + " 次加载数据---");
                        return getValue(key);
                    }
                });

        System.err.println("第一次读取缓存："+cache.getIfPresent("test"));
        try {
            getValue("other/other/123");
            Thread.sleep(6000);
            System.err.println("第2次读取缓存："+cache.getIfPresent("test"));
            getValue("456");
            Thread.sleep(6000);
            System.err.println("第3次读取缓存："+cache.getIfPresent("test"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getValue(String... value) {
        if(null == value) {
            list.add("默认值");
        }
        list.addAll(Arrays.asList(value));
        return Joiner.on("-").join(list);
    }

}
