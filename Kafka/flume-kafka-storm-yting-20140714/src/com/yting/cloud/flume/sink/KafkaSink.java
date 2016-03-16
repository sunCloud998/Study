package com.yting.cloud.flume.sink;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;

/**
 * KafkaSink (custom)
 * 
 * @Author ����ͥ
 * @Time 2014-07-14
 * @Problem kafka num.partitions fall flat
 *
 */
public class KafkaSink extends AbstractSink implements Configurable {
	private static final Log logger = LogFactory.getLog(KafkaSink.class);

	private String topic;
	private Producer<String, String> producer;

	@Override
	public void configure(Context context) {
		topic = "flume-kafka-storm-001";
		Properties props = new Properties();
		props.setProperty("metadata.broker.list", "rs229:9092");
		props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
		props.put("partitioner.class", "com.yting.cloud.kafa.partition.HashSimplePartitioner");
		props.put("zookeeper.connect", "rs229:2181,rs227:2181,rs226:2181,rs198:2181,rs197:2181/kafka");
		props.setProperty("num.partitions", "5"); // ����Ч
		// props.setProperty("producer.type", "async");
		// props.setProperty("batch.num.messages", "1");
		props.put("request.required.acks", "1");
		ProducerConfig config = new ProducerConfig(props);
		producer = new Producer<String, String>(config);
		logger.info("===============================================================================");
		logger.info("KafkaSink initialize is successful .");

	}

	@Override
	public Status process() throws EventDeliveryException {
		Channel channel = getChannel();
		Transaction tx = channel.getTransaction();
		try {
			tx.begin();
			Event e = channel.take();
			if (e == null) {
				tx.rollback();
				return Status.BACKOFF;
			}
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, new String(e.getBody()));
			producer.send(data);
			logger.info("Flume message to kafka : {}" + new String(e.getBody()));
			tx.commit();
			return Status.READY;
		} catch (Exception e) {
			logger.error("Flume KafkaSinkException:{}", e);
			tx.rollback();
			return Status.BACKOFF;
		} finally {
			tx.close();
		}
	}
}
