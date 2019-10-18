package com.sfy.java.zookeeper.curatorapi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @function: 异步处理
 * @description: AsyncProcess.java
 * @date: 2019/08/11
 * @author: sunfayun
 * @version: 1.0
 */
public class AsyncProcess {

    private final static String hostInfo = "127.0.0.1:2181";
    private ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,3, Integer.MAX_VALUE);
    private CuratorFramework curatorFramework;

    @Before
    public void init() {
        curatorFramework = CuratorFrameworkFactory.newClient(hostInfo, 3000, 2000, retryPolicy);
        curatorFramework.start();
    }

    @Test
    public void createNotAsync() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.err.println("code:"+curatorEvent.getResultCode()+",type:"+curatorEvent.getType());
                        System.err.println("thread info:" + Thread.currentThread().getName());
                        System.err.println("context:"+curatorEvent.getContext());
                        countDownLatch.countDown();
                    }
                }, "异步传输数据", Executors.newFixedThreadPool(1))
                .forPath("/sft-test");
        countDownLatch.await();
    }

}
