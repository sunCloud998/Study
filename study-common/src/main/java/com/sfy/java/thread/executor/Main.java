package com.sfy.java.thread.executor;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/24
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        for (int i=0;i<100;i++){
            Task task = new Task("Task "+i);
            server.executeTask(task);
        }
        server.endServer();
    }

}
