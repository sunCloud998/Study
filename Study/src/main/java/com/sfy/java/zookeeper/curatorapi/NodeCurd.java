package com.sfy.java.zookeeper.curatorapi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

/**
 * @function:
 * @description: NodeCurd.java
 * @date: 2019/08/09
 * @author: sunfayun
 * @version: 1.0
 */
public class NodeCurd {

    private final static String hostInfo = "127.0.0.1:2181";
    private ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,3, Integer.MAX_VALUE);
    private CuratorFramework curatorFramework;

    @Before
    public void init() {
        curatorFramework = CuratorFrameworkFactory.builder().connectString(hostInfo)
                .sessionTimeoutMs(10000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        curatorFramework.start();
    }

    @Test
    public void createNode() throws Exception {
        curatorFramework.create()
                .creatingParentsIfNeeded() // 递归创建，如果父节点不存在创建父节点
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/sfy-test", "sunCloud".getBytes());
    }

    @Test
    public void getData() throws Exception {
        Stat stat = new Stat();
        byte[] data = curatorFramework.getData()
                .storingStatIn(stat)
                .forPath("/sfy-test");
        System.err.println("data:" + new String(data));
        System.err.println("version:" + stat.getVersion());
    }

    @Test
    public void getDataWithWatcher() throws Exception {
        byte[] data = curatorFramework.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.err.println("==watcher事件触发==");
                System.err.println("==Event==："+watchedEvent);
            }
        }).forPath("/sfy-test");
        Thread.sleep(5000);
        System.err.println("watcher data:" + new String(data));
    }

    @Test
    public void setData() throws Exception {

        this.getDataWithWatcher();

        Stat stat = curatorFramework.setData()
                .withVersion(-1)
                .forPath("/sfy-test", "sunCloud998".getBytes());
        System.err.println("update version:"+stat.getVersion());
    }

    @Test
    public void deleteNode() throws Exception {
        Void vd = curatorFramework.delete().forPath("/sfy-test");
        System.err.println("Void:"+vd);
    }

    /**
     * 递归删除
     * @throws Exception
     */
    @Test
    public void deleteNode01() throws Exception {
        curatorFramework.delete()
                .deletingChildrenIfNeeded()
                .forPath("/sfy-test");
    }

    @Test
    public void exists() throws Exception {
        Stat nodeStat = curatorFramework.checkExists().forPath("/sfy-test");
        if(nodeStat == null) {
            System.err.println("节点不存在");
            return;
        }
        if(nodeStat.getEphemeralOwner() > 0) {
            System.err.println("临时节点");
        } else {
            System.err.println("永久节点");
        }
    }

}
