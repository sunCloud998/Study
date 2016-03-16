package com.yting.cloud.kafa.consumer;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.ConsumerIterator;
//import kafka.consumer.KafkaStream;
 
/**
 * KafkaSink (custom)
 * 
 * @Author Õı—ÔÕ•
 * @Time 2014-07-14
 * @Problem you should run this consumer class before producer
 *
 */
//@SuppressWarnings("all")
public class MyHighLevelConsumer {
    private final ConsumerConnector consumer;
    private final String topic;
    private ExecutorService executor;
 
    public MyHighLevelConsumer(String a_zookeeper, String a_groupId, String a_topic) {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig(a_zookeeper, a_groupId));
        this.topic = a_topic;
    }
 
    public void shutdown() {
        if (consumer != null) consumer.shutdown();
        if (executor != null) executor.shutdown();
    }
 
    public void run(int a_numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(a_numThreads));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
 
        // now launch all the threads
        //
        executor = Executors.newFixedThreadPool(a_numThreads);
 
        // now create an object to consume the messages
        //
        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.submit(new ConsumerTest(stream, threadNumber));
            threadNumber++;
        }
    }
 
    private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", a_zookeeper);
        props.put("group.id", a_groupId);
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
//        props.put("auto.offset.reset", "smallest");
 
        return new ConsumerConfig(props);
    }
    

    class ConsumerTest implements Runnable {
        private KafkaStream<byte[], byte[]> m_stream;
        private int m_threadNumber;
     
        public ConsumerTest(KafkaStream<byte[], byte[]> a_stream, int a_threadNumber) {
            m_threadNumber = a_threadNumber;
            m_stream = a_stream;
        }
     
        public void run() {
            ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
            while (it.hasNext())
                System.out.println("Thread " + m_threadNumber + ": " + new String(it.next().message()));
            System.out.println("Shutting down Thread: " + m_threadNumber);
        }
    }
    
    public static void main(String[] args) {
//      String zooKeeper = args[0];
//      String groupId = args[1];
//      String topic = args[2];
//      int threads = Integer.parseInt(args[3]);

//      String zooKeeper = "116.255.224.229:2182";
      String zooKeeper = "rs229:2181,rs227:2181,rs226:2181,rs198:2181,rs197:2181/kafka";
      String groupId = "1";
      String topic = "flume-kafka-storm-001";
      int threads = 1;

      MyHighLevelConsumer example = new MyHighLevelConsumer(zooKeeper, groupId, topic);
      example.run(threads);

//      try {
//          Thread.sleep(1000);
//      } catch (InterruptedException ie) {
//
//      }
//      example.shutdown();
  }
}
