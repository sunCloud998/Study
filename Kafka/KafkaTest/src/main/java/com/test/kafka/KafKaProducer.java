package com.test.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * @Description: KafKaProducer.java
 * @Author: sunfayun
 * @Date: 2015/08/20
 * @Time: 下午3:25
 * @Version 1.0
 */
public class KafKaProducer extends Thread {

    private Producer<Integer,String> producer;
    private String topic;
    private Properties properties = new Properties();
    private final int sleep = 1000 * 3;

    public KafKaProducer(String topic){
        properties.put("serializer.class","kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list","localhost:9092");
        producer = new Producer<Integer, String>(new ProducerConfig(properties));
        this.topic = topic;
    }

    @Override
    public void run() {
        int offsetNo = 1;
        while(true){
            String msg = new String("Message_"+offsetNo);
            System.err.println("Send->"+msg);
            producer.send(new KeyedMessage<Integer, String>(topic, msg));
            offsetNo++;

            try {
                sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
