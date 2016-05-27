package com.sfy.java.guava.cache;

import com.google.common.cache.*;
import com.sfy.object.Student;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Description: CacheTest.java
 * @Date: 2016/03/16
 * @Author: sunfayun
 * @Version: 1.0
 */
public class CacheTest {

    @Test
    public void cacheTest() {
        //缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
        LoadingCache<String,Student> loadingCache = CacheBuilder.newBuilder()//CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                //设置写缓存后8秒钟过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(100)
                //设置要统计缓存的命中率
                .recordStats()
                //设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
                        System.err.println(removalNotification.getKey() + " was removed cause is " + removalNotification.getCause());
                    }
                }).build(//build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                        new CacheLoader<String, Student>() {
                            @Override
                            public Student load(String key) throws Exception {
                                //load from DB
                                System.err.println("load Student:" + key);
                                Student student = new Student();
                                student.setNo(key);
                                student.setName("name " + key);
                                return student;
                            }
                        }
                );

        for (int i=0;i<20;i++){
            try {
                //从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
                Student student = loadingCache.get("test");
                System.err.println(student);
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.err.println("cache status:");
        //最后打印缓存的命中率等 情况
        System.err.println(loadingCache.stats().toString());
    }

}
