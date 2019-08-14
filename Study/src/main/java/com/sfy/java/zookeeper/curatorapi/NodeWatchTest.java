package com.sfy.java.zookeeper.curatorapi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @function: 事件监听
 * @description: WatchTest.java
 * @date: 2019/08/11
 * @author: sunfayun
 * @version: 1.0
 */
public class NodeWatchTest {

    private final static String hostInfo = "127.0.0.1:2181";
    private ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3, Integer.MAX_VALUE);
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private CuratorFramework client;
    private NodeCache nodeCache;

    @Before
    public void init() {
        client = CuratorFrameworkFactory.newClient(hostInfo, 3000, 2000, retryPolicy);
        client.start();
    }

    public void nodeWatchTest(String path) throws Exception {
        nodeCache = new NodeCache(client, path, false);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData childData = nodeCache.getCurrentData();
                String data = childData == null ? "" : new String(childData.getData());
                System.err.println("node data update, new data is :" + data);
                countDownLatch.countDown();
            }
        });
    }

    @Test
    public void createTest() throws Exception {
        String path = "/sfy-test";
        this.nodeWatchTest(path);
        client.create().forPath(path, "init".getBytes());
        countDownLatch.await();
    }

    @Test
    public void setDataTest() throws Exception {
        String path = "/sfy-test";
        this.nodeWatchTest(path);
        client.setData().forPath(path, "sfy-test".getBytes());
        countDownLatch.await();
    }

    @Test
    public void deleteNodeTest() throws Exception {
        String path = "/sfy-test";
        this.nodeWatchTest(path);
        client.delete().deletingChildrenIfNeeded().forPath(path);
        countDownLatch.await();
    }

}
