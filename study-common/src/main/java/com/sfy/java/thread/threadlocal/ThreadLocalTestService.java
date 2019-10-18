package com.sfy.java.thread.threadlocal;

import lombok.Getter;

/**
 * @function:
 * @description: ThreadLocalTestService.java
 * @date: 2018/08/20
 * @author: sunfayun
 * @version: 1.0
 */
@Getter
public class ThreadLocalTestService {

    private ThreadLocal<ThreadSaveInfoVo> threadLocal = new ThreadLocal<>();

    public void buildThreadLocalInfo() {
        threadLocal.set(new ThreadSaveInfoVo(1,"张三"));
    }

    public void releaseThreadLocal() {
        threadLocal.remove();
    }

}
