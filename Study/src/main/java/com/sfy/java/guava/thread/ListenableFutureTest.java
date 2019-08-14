package com.sfy.java.guava.thread;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @function:
 * @description: ListenableFutureTest.java
 * @date: 2019/04/18
 * @author: sunfayun
 * @version: 1.0
 */
public class ListenableFutureTest {

    @Test
    public void test() throws Exception {
        ListeningExecutorService service1 = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Object> submit1 = service1.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(4000);
                System.out.println("call future1");
                return "1";
            }
        });
        com.google.common.util.concurrent.ListenableFuture<Object> submit2 = service1.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(1000);
                System.out.println("call future2");
                return "2";
            }
        });
        ListenableFuture<List<Object>> listListenableFuture = Futures.allAsList(submit1, submit2);
        ListenableFuture<String> transform = Futures.transform(listListenableFuture, new AsyncFunction<List<Object>, String>() {
            @Override
            public com.google.common.util.concurrent.ListenableFuture<String> apply(List<Object> input) throws Exception {
                System.err.println(String.format("apply %s",input));
                return Futures.immediateFuture(String.format("success future %s",input));
            }
        });

        Futures.addCallback(transform, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result.getClass());
                System.out.println("success:"+result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("error:" + t.getMessage());
            }
        });
        //获取值
        System.out.println("transform:"+transform.get(3000, TimeUnit.SECONDS));
    }

}
