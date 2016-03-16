package com.yting.cloud.storm.spout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.common.PathUtils;

import com.yting.cloud.kafa.consumer.MyHighLevelConsumer;
import com.yting.cloud.kafa.consumer.MySimpleConsumer;

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

public class SimpleKafkaSpout implements IRichSpout {
	private static final Log log = LogFactory.getLog(SimpleKafkaSpout.class);
	private SpoutOutputCollector collector;
	private ConsumerConnector consumer;
	private String topic;
	private int a_numThreads = 1;

	public SimpleKafkaSpout(String topic) {
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
		log.info("--------->activate start--------->");
//		MyHighLevelConsumer.main(null);
		MySimpleConsumer.main(null);
		log.info("--------->activate end--------->");
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
		declarer.declare(new Fields("highLevelKafkaSpout"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		log.info("--------->getComponentConfiguration");
		return null;
	}

}
