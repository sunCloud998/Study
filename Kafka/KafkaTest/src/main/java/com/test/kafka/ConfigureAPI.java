package com.test.kafka;

/**
 * @Description: ConfigureAPI.java
 * @Author: sunfayun
 * @Date: 2015/08/20
 * @Time: 下午2:13
 * @Version 1.0
 */
public interface ConfigureAPI {

    String zk = "127.0.0.1:2181";
    String group_id = "test_group";
    String topic = "test";
    String broker_list = "127.0.0.1:9092";
    int buffer_size = 64 * 1024;
    int timeout = 20000;
    int interval = 10000;

}
