package com.sfy.java.zookeeper.curatorapi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

/**
 * @function: 事务操作
 * @description: TransactionProcess.java
 * @date: 2019/08/11
 * @author: sunfayun
 * @version: 1.0
 */
public class TransactionProcess {

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
    public void transactionProcTest() throws Exception {
        CuratorOp createParentNode = curatorFramework.transactionOp().create().forPath("/test","parent data".getBytes());
        CuratorOp createChildNode = curatorFramework.transactionOp().create().forPath("/test/sfy", "children data".getBytes());
        CuratorOp setParentData = curatorFramework.transactionOp().setData().forPath("/test", "parent data new value".getBytes());
        CuratorOp deleteParent = curatorFramework.transactionOp().delete().forPath("/test");
        Collection<CuratorTransactionResult> resultCollection = curatorFramework.transaction().forOperations(createParentNode,createChildNode,setParentData,deleteParent);
        for (CuratorTransactionResult curatorTransactionResult : resultCollection) {
            System.err.println(curatorTransactionResult.getForPath() + "-" + curatorTransactionResult.getType());
        }
    }

}
