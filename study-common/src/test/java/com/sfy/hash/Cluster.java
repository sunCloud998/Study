package com.sfy.hash;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 集群
 * @Description: Cluster.java
 * @User: sunfayun
 * @Date: 2017/05/16
 * @Version: 1.0
 */
public abstract class Cluster {

    protected List<Node> nodes;

    public Cluster(){
        nodes = Lists.newArrayList();
    }

    public abstract void addNode(Node node);

    public abstract void removeNode(Node node);

    public abstract Node get(Object k);

}
