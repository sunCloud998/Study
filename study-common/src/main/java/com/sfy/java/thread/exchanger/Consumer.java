package com.sfy.java.thread.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Function:
 * @Description: Consumer.java
 * @Date: 2016/06/24
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Consumer implements Runnable {

    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer,Exchanger<List<String>> exchanger){
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for(int i=0;i<10;i++){
            System.out.printf("Consumer: Cycle %d\n",cycle);
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j=0;j<10;j++){
                String message = buffer.get(0);
                System.out.println("Consumer: "+message);
                buffer.remove(0);
            }
            cycle++;
        }
    }
}
