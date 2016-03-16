package com.test.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: KafkaConsumer.java
 * @Author: sunfayun
 * @Date: 2015/08/20
 * @Time: 下午2:16
 * @Version 1.0
 */
public class KafkaConsumer extends Thread {

    private ConsumerConnector consumerConnector;
    private String topic;
    private final int sleep = 1000 * 3;

    public KafkaConsumer(String topic){
        consumerConnector = (ConsumerConnector) Consumer.createJavaConsumerConnector(this.consumerConfig());
        this.topic = topic;
    }

    private ConsumerConfig consumerConfig() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect",ConfigureAPI.zk);
        properties.put("group.id",ConfigureAPI.group_id);
        properties.put("zookeeper.session.timeout.ms","40000");
        properties.put("zookeeper.sync.time.ms","200");
        properties.put("auto.commit.interval.ms","1000");
        return new ConsumerConfig(properties);
    }

    @Override
    public void run() {
        Map<String,Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic,new Integer(1));
        Map<String,List<KafkaStream<byte[],byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        KafkaStream<byte[],byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[],byte[]> it = stream.iterator();
        while(it.hasNext()){
            System.err.println("Receive->"+new String((it.next().message())));
            try {
                sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
