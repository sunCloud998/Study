package com.yting.cloud.kafa.producer;

import java.util.*;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * Kafka官网给的案例 Producer，饿在Eclipse下本地连接服务器测试，所以修改了一些代码
 * 
 * @Author 王扬庭
 * @Time 2014-07-01
 *
 */
public class TestProducer {
	public static void main(String[] args) {
//		long events = Long.parseLong(args[0]);
		long events = 100;
		Random rnd = new Random();

		Properties props = new Properties();
//		props.put("metadata.broker.list", "broker1:9092,broker2:9092");
		props.put("metadata.broker.list", "rs229:9092"); // Eclipse 下rs229在本地hosts也要配置，或者写成ip形式也可以
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("partitioner.class", "com.yting.cloud.kafka.partition.TestSimplePartitioner");
		props.put("zookeeper.connect", "rs229:2181,rs227:2181,rs226:2181,rs198:2181,rs197:2181/kafka");
		props.put("num.partitions", "5");
		props.put("request.required.acks", "1");

		ProducerConfig config = new ProducerConfig(props);

		Producer<String, String> producer = new Producer<String, String>(config);

		for (long nEvents = 0; nEvents < events; nEvents++) {
			long runtime = new Date().getTime();
			String ip = "13.14.20." + rnd.nextInt(255);
			String msg = "id---> " + (nEvents+1) + " ---> " + runtime + ",www.ytingxmei1106.com," + ip; // 改成这样是为了使用Consumer的时候便于查看各个分区的数据
			System.out.println(msg);
//			KeyedMessage<String, String> data = new KeyedMessage<String, String>("yting_page_visits", ip, msg);
			KeyedMessage<String, String> data = new KeyedMessage<String, String>("test002", ip, msg);
			producer.send(data);
		}
		producer.close();
		
		System.out.println("ok");
	}
}
