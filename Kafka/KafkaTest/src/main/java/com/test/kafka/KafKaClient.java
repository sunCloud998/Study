package com.test.kafka;

import static com.test.kafka.ConfigureAPI.topic;

/**
 * @Description: KafKaClient.java
 * @Author: sunfayun
 * @Date: 2015/08/20
 * @Time: 下午3:32
 * @Version 1.0
 */
public class KafKaClient {

    public static void main(String[] args) {
//        KafKaProducer producer = new KafKaProducer(topic);
//        producer.start();

        KafkaConsumer consumer = new KafkaConsumer(ConfigureAPI.topic);
        consumer.start();
    }

}
