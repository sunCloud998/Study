package com.sfy.hash;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @Description: HashClusterTest.java
 * @User: sunfayun
 * @Date: 2017/05/16
 * @Version: 1.0
 */
public class HashClusterTest {

    private Cluster cluster;
    private int dataCount = 50000;
    private String pre_key = "c";

    @Before
    public void init(){
//        cluster = new NormalHashCluster();
        cluster = new ConsistencyHashCluster();
        cluster.addNode(new Node("c1.test.info", "192.168.0.1"));
        cluster.addNode(new Node("c2.test.info", "192.168.0.2"));
        cluster.addNode(new Node("c3.test.info", "192.168.0.3"));
        cluster.addNode(new Node("c4.test.info", "192.168.0.4"));

        cluster.addNode(new Node("c5.test.info", "192.168.0.5"));
        cluster.addNode(new Node("c6.test.info", "192.168.0.6"));
//        cluster.addNode(new Node("c7.test.info", "192.168.0.7"));
//        cluster.addNode(new Node("c8.test.info", "192.168.0.8"));
//        cluster.addNode(new Node("c9.test.info", "192.168.0.9"));
    }

    @Test
    public void test01(){
        IntStream.range(0,dataCount).forEach(index -> {
            Node node = cluster.get(pre_key + index);
            node.put(pre_key + index, "TEST DATA");
        });

        System.err.println("数据分布情况：");
        cluster.nodes.forEach(node -> {
            System.err.println("IP:"+node.getIp()+", 数据量："+node.getData().size());
        });
        //命中率
        long hitCount = IntStream.range(0,dataCount).filter(index -> cluster.get(pre_key + index).get(pre_key + index) != null).count();
        System.err.println("缓存命中率："+hitCount * 1f / dataCount);
    }

}
