package com.yting.cloud.kafa.partition;


import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Kafka官网给的案例 SimplePartitioner，官网给的是0.8.0的版本，跟0.8.1的版本不一样，所以改了下，你懂的！
 *
 * @Author 王扬庭
 * @Time 2014-07-01
 */
public class HashSimplePartitioner implements Partitioner {
    public HashSimplePartitioner(VerifiableProperties props) {

    }

    // public int partition(String key, int a_numPartitions) {
    // int partition = 0;
    // int offset = key.lastIndexOf('.');
    // if (offset > 0) {
    // partition = Integer.parseInt(key.substring(offset + 1)) %
    // a_numPartitions;
    // }
    // return partition;
    // }

    @Override
    public int partition(Object obj, int a_numPartitions) {
//		String key = obj.toString();
        int partition = 0;
//		int offset = key.lastIndexOf('.');
//		if (offset > 0) {
//			partition = Integer.parseInt(key.substring(offset + 1)) % a_numPartitions;
//		}

        partition = obj.hashCode() % a_numPartitions;

        return partition;
    }

}
