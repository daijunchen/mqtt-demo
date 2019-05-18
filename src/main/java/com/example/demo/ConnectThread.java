package com.example.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @PACKAGE_NAME: com.example.demo
 * @AUTHOR: JcD
 * @DATE: 2019/5/15
 * @PROJECT_NAME: demo
 **/

public class ConnectThread extends Thread {
    @Override
    public void run() {
        super.run();
        String id = "test-"+System.currentTimeMillis();
        Connect connect = new Connect(id);
        BlockingQueue blockingQueue = new ArrayBlockingQueue(2);
        try {
            blockingQueue.put(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
