package com.yting.cloud.storm.spout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

/**
 * Storm spout
 * 
 * @Author ÍõÑïÍ¥
 * @Time 2014-07-14
 *
 */
public class KafkaSpout implements IRichSpout {
	private static final Log log = LogFactory.getLog(KafkaSpout.class);
	private SpoutOutputCollector collector;
	private ConsumerConnector consumer;
	private String topic;
	private int a_numThreads = 1;

	public KafkaSpout(String topic) {
		this.topic = topic;
	}

	@Override
	public void nextTuple() {

	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void ack(Object msgId) {
		log.info("--------->ack");
	}

	@Override
	public void activate() {
		log.info("--------->activate");
		this.consumer = Consumer.createJavaConsumerConnector(createConsumerConfig());
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(a_numThreads));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> streams = consumerMap.get(topic).get(0);
		ConsumerIterator<byte[], byte[]> it = streams.iterator();
		while (it.hasNext()) {
			String value = new String(it.next().message());
			log.info("(--------->Storm kafka consumer)------->" + value);
			collector.emit(new Values(value), value);
		}
	}

	private static ConsumerConfig createConsumerConfig() {
		Properties props = new Properties();
		props.put("zookeeper.connect", "rs229:2181,rs227:2181,rs226:2181,rs198:2181,rs197:2181/kafka");
//		props.put("zookeeper.connect", "rs229:2181,rs227:2181,rs226:2181,rs198:2181,rs197:2181");
//		props.put("zookeeper.connect","rs229");
		props.put("group.id", "2");
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");

		return new ConsumerConfig(props);
	}

	@Override
	public void close() {
		log.info("--------->close");
	}

	@Override
	public void deactivate() {
		log.info("--------->deactivate");
	}

	@Override
	public void fail(Object msgId) {
		log.info("--------->fail");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("kafkaSpout"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		log.info("--------->getComponentConfiguration");
		return null;
	}

}
