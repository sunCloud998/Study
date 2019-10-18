package com.sfy.hash;

/**
 * 取摸方式
 * @Description: NormalHashCluster.java
 * @User: sunfayun
 * @Date: 2017/05/16
 * @Version: 1.0
 */
public class NormalHashCluster extends Cluster {

    public NormalHashCluster(){
        super();
    }

    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
    }

    @Override
    public void removeNode(Node node) {
        this.nodes.removeIf(o -> o.getIp().equals(node.getIp()) || o.getDomain().equals(node.getDomain()));
    }

    @Override
    public Node get(Object k) {
        long hash = hash(k);
        long index = hash % nodes.size();
        return nodes.get((int) index);
    }

    private long hash(Object k) {
        int h;
        h = (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
        return (h & 0x7FFFFFFF);//& 0x7FFFFFFF确保hash的结果是正数
    }
}
