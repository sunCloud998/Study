package com.sfy.java.zookeeper.curatorapi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

/**
 * @function:
 * @description: CreateSession.java
 * @date: 2019/08/09
 * @author: sunfayun
 * @version: 1.0
 */
public class CreateSession {

    private final static String hostInfo = "127.0.0.1:2181";

    /**
     * 随着重试次数增加重试时间间隔变大,指数倍增长baseSleepTimeMs * Math.max(1, random.nextInt(1 << (retryCount + 1)))。
     * 有两个构造方法
     * baseSleepTimeMs初始sleep时间
     * maxRetries最多重试几次
     * maxSleepMs最大的重试时间
     * 如果在最大重试次数内,根据公式计算出的睡眠时间超过了maxSleepMs，将打印warn级别日志,并使用最大sleep时间。
     * 如果不指定maxSleepMs，那么maxSleepMs的默认值为Integer.MAX_VALUE。
     */
    private ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,3, Integer.MAX_VALUE);

    /**
     * connectionString zk地址
     * retryPolicy 重试策略
     * 默认的sessionTimeoutMs为60000
     * 默认的connectionTimeoutMs为15000
     */
    @Test
    public void testCreate01() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(hostInfo, retryPolicy);
        curatorFramework.start();
    }

    /**
     * connectionString zk地址
     * sessionTimeoutMs 会话超时时间
     * connectionTimeoutMs 连接超时时间
     * retryPolicy 重试策略
     */
    @Test
    public void testCreate02() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(hostInfo, 60000, 15000, retryPolicy);
        curatorFramework.start();
    }

    /**
     * connectionString zk地址
     * sessionTimeoutMs 会话超时时间
     * connectionTimeoutMs 连接超时时间
     * namespace 每个curatorFramework 可以设置一个独立的命名空间,之后操作都是基于该命名空间，比如操作 /app/message 其实操作的是/sfy/app/message
     * retryPolicy 重试策略
     */
    @Test
    public void testCreate03() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(hostInfo)
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(15000)
                .namespace("/sfy")
                .retryPolicy(retryPolicy)
                .build();
        curatorFramework.start();
    }

}
