package com.sfy.java.zookeeper.curatorapi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @function:
 * @description: TreeCacheWatcher.java
 * @date: 2019/08/15
 * @author: sunfayun
 * @version: 1.0
 */
public class TreeCacheWatcher {

    private final static String hostInfo = "127.0.0.1:2181";
    private ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,3, Integer.MAX_VALUE);
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private CuratorFramework client = CuratorFrameworkFactory.builder().connectString(hostInfo)
            .sessionTimeoutMs(10000)
            .connectionTimeoutMs(5000)
            .retryPolicy(retryPolicy)
            .build();;
    private TreeCache cache;
    private String rootPath = "/sfy-test";

    private void treeWatcherTest() throws Exception {
        client.start();
        cache = new TreeCache(client, rootPath);
        cache.start();

        // 添加错误处理监听器
        cache.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
            @Override
            public void unhandledError(String s, Throwable throwable) {
                System.err.println("-->错误原因："+throwable.getMessage());
            }
        });

        // 节点变化监听
        cache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                switch (treeCacheEvent.getType()) {
                    case INITIALIZED:
                        System.err.println("=> initialized 初始化");
                        break;
                    case NODE_ADDED:
                        System.err.println("=> node add:" + treeCacheEvent.getData().getPath() + ",数据："+treeCacheEvent.getData().getData());
                        break;
                    case NODE_REMOVED:
                        System.err.println("=> node remove:" + treeCacheEvent.getData().getPath() + ",数据："+ treeCacheEvent.getData().getData());
                        break;
                    case NODE_UPDATED:
                        System.err.println("=> node update:" + treeCacheEvent.getData().getPath() + ",数据："+treeCacheEvent.getData().getData());
                        break;
                    default:
                        System.err.println("=> treeCacheEventType:" + treeCacheEvent.getType());
                        break;
                }
            }
        });
    }

    private void createRootNodeTest() throws Exception {
        client.create().withMode(CreateMode.PERSISTENT).forPath(rootPath, "init".getBytes());
        Thread.sleep(1000);
    }

    private void createChildNodeTest() throws Exception {
        client.create().withMode(CreateMode.PERSISTENT).forPath(rootPath + "/cloud");
        Thread.sleep(1000);
    }

    private void setChildNodeDataTest() throws Exception {
        client.setData().forPath(rootPath + "/cloud", "hello world".getBytes());
        Thread.sleep(1000);
    }

    private void deleteNodeTest() throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath(rootPath);
        Thread.sleep(1000);
    }

    @Test
    public void treeCacheWatcherTest() throws Exception {
        this.treeWatcherTest();
        this.createRootNodeTest();
        this.createChildNodeTest();
        this.setChildNodeDataTest();
        this.deleteNodeTest();
    }

}
