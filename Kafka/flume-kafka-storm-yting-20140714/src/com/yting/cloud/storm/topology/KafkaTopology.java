package com.yting.cloud.storm.topology;

import java.util.HashMap;
import java.util.Map;

import com.yting.cloud.storm.spout.HighLevelKafkaSpout;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

/**
 * Storm topology
 * 
 * @Author Õı—ÔÕ•
 * @Time 2014-07-14
 *
 */
public class KafkaTopology {
	public static void main(String[] args) {
		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("1", new HighLevelKafkaSpout(""), 1);

		Map conf = new HashMap();
		conf.put(Config.TOPOLOGY_WORKERS, 1);
		conf.put(Config.TOPOLOGY_DEBUG, true);

		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("my-flume-kafka-storm-topology-integration", conf, builder.createTopology());
		
		Utils.sleep(1000*60*5); // local cluster test ...
		cluster.shutdown();
	}
}
