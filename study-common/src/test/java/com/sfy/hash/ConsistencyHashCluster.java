package com.sfy.hash;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * @Description: ConsistencyHashCluster.java
 * @User: sunfayun
 * @Date: 2017/05/17
 * @Version: 1.0
 */
public class ConsistencyHashCluster extends Cluster {

    private SortedMap<Long,Node> virNodes = new TreeMap();
    private static final int VIR_NODE_COUNT = 512;
    private static final String SPLIT = "#";

    public ConsistencyHashCluster(){
        super();
    }

    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
        IntStream.range(0,VIR_NODE_COUNT).forEach(index -> {
            long hash = hash(node.getIp() + SPLIT + index);
            virNodes.put(hash, node);
        });
    }

    @Override
    public void removeNode(Node node) {
        nodes.removeIf(o -> node.getIp().equals(o.getIp()));
        IntStream.range(0,VIR_NODE_COUNT).forEach(index -> {
            long hash = hash(node.getIp() + SPLIT + index);
            virNodes.remove(hash);
        });
    }

    @Override
    public Node get(Object k) {
        long hash = hash(k);
        SortedMap<Long,Node> subMap = hash >= virNodes.lastKey() ? virNodes.tailMap(0L) : virNodes.tailMap(hash);
        if(subMap.isEmpty()){
            return null;
        }
        return subMap.get(subMap.firstKey());
    }

    public int hash(Object k){
        int h;
        h = (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
        return (h & 0x7FFFFFFF);//& 0x7FFFFFFF确保hash的结果是正数
    }
}
