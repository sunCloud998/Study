package com.sfy.java.zookeeper.curatorapi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @function:
 * @description: ChildrenNodeWatchTest.java
 * @date: 2019/08/11
 * @author: sunfayun
 * @version: 1.0
 */
public class ChildrenNodeWatchTest {

    private final static String hostInfo = "127.0.0.1:2181";
    private ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,3, Integer.MAX_VALUE);
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(hostInfo)
            .sessionTimeoutMs(10000)
            .connectionTimeoutMs(5000)
            .retryPolicy(retryPolicy)
            .build();;
    private PathChildrenCache cache;
    private String rootPath = "/sfy-test";

    public void childrenWatchTest() throws Exception {
        curatorFramework.start();
        cache = new PathChildrenCache(curatorFramework, rootPath, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()) {
                    case CHILD_ADDED:
                        System.err.println("-> child add:"+pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.err.println("-> child remove:" + pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.err.println("-> child update:" + pathChildrenCacheEvent.getData().getPath());
                        break;
                    default:
                        System.err.println("-> other event:" + pathChildrenCacheEvent.getType().name());
                        break;
                }
            }
        });
    }

    private void createRootNodeTest() throws Exception {
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(rootPath, "init".getBytes());
        Thread.sleep(1000);
    }

    private void createChildNodeTest() throws Exception {
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(rootPath + "/cloud");
        Thread.sleep(1000);
    }

    private void setDataTest() throws Exception {
        curatorFramework.setData().forPath(rootPath + "/cloud", "hello world".getBytes());
        Thread.sleep(1000);
    }

    private void deleteChildNodeTest() throws Exception {
        curatorFramework.delete().forPath(rootPath + "/cloud");
        Thread.sleep(1000);
    }

    private void deleteRootNodeTest() throws Exception {
        curatorFramework.delete().forPath(rootPath);
        Thread.sleep(1000);
    }

    @Test
    public void watcherTest() throws Exception {
        this.childrenWatchTest();
        this.createRootNodeTest();
        this.createChildNodeTest();
        this.setDataTest();
        this.deleteChildNodeTest();
        this.deleteRootNodeTest();
        cache.close();
        curatorFramework.close();
    }

}
