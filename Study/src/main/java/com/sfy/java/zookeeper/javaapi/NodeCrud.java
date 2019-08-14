package com.sfy.java.zookeeper.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * @function:
 * @description: NodeCrud.java
 * @date: 2019/08/09
 * @author: sunfayun
 * @version: 1.0
 */
public class NodeCrud {

    private final static String hostInfo = "127.0.0.1:2181";
    private final static Integer sessionTimeOut = 1000;
    private final static boolean canBeReadOnly = false;
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private Stat stat = new Stat();
    private ZooKeeper zk;

    @Before
    public void init() {
        try {
            zk = new ZooKeeper(hostInfo, sessionTimeOut, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                        countDownLatch.countDown();
                        System.err.println("====连接已建立====");
                    }
                }
            }, canBeReadOnly);

            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateNode() {
        try {
            zk.create("/sfy-test", "sunCloud".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getNodeData() {
        try {
            byte[] data = zk.getData("/sfy-test", null, stat);
            System.err.println("->"+new String(data, StandardCharsets.UTF_8));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void updateNodeTest() {
        try {
            zk.setData("/sfy-test", "sunCloud998".getBytes(),stat.getVersion());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void deleteNodeTest() {
        try {
            // -1表示不区分版
            zk.delete("/sfy-test", -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } finally {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
