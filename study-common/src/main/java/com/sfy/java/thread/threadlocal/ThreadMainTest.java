package com.sfy.java.thread.threadlocal;

/**
 * @function:
 * @description: ThreadMainTest.java
 * @date: 2018/08/20
 * @author: sunfayun
 * @version: 1.0
 */
public class ThreadMainTest {

    public static void main(String[] args) {
        ThreadLocalTestService threadLocalTestService = new ThreadLocalTestService();
        threadLocalTestService.buildThreadLocalInfo();
        ThreadLocal<ThreadSaveInfoVo> threadSaveInfoVoThreadLocal = threadLocalTestService.getThreadLocal();
        ThreadSaveInfoVo threadSaveInfoVo = threadSaveInfoVoThreadLocal.get();
        System.err.println(threadSaveInfoVo);
        threadSaveInfoVoThreadLocal.remove();
    }
}
