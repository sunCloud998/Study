package com.sfy.java.guava.base;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @function:
 * @description: StopWatchTest.java
 * @date: 2018/12/27
 * @author: sunfayun
 * @version: 1.0
 */
public class StopWatchTest {

    private Random random = new Random();

    @Test
    public void stopWatchTest01() throws InterruptedException {
        for(int i=0;i<10;i++) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            int stopTime = random.nextInt(1) + 1;
            Thread.sleep(stopTime * 1000);
            this.invokeRemoteMethodMock(i);
            System.err.println("   =>"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    private void invokeRemoteMethodMock(int i) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        int time = random.nextInt(3) +1;
        Thread.sleep(time * 1000);
        System.err.println(i + "=>"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

}
