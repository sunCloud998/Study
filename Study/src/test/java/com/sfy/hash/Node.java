package com.sfy.hash;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * @Description: Node.java
 * @User: sunfayun
 * @Date: 2017/05/16
 * @Version: 1.0
 */
@Data
public class Node<K,V> {

    private String domain;
    private String ip;
    private Map<K,V> data = Maps.newConcurrentMap();

    public Node(){}

    public Node(String domain,String ip){
        this.domain = domain;
        this.ip = ip;
    }

    public void put(K k, V v){
        data.put(k,v);
    }

    public void remove(K k){
        data.remove(k);
    }

    public V get(K k){
        return data.get(k);
    }

}
