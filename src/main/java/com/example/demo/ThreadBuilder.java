package com.example.demo;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @PACKAGE_NAME: com.example.demo
 * @AUTHOR: JcD
 * @DATE: 2019/5/15
 * @PROJECT_NAME: demo
 **/

public class ThreadBuilder extends Thread{
    @Override
    public void run() {
        super.run();
        int count = 50;
        ArrayList<ConnectThread> pool = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ConnectThread connectThread = new ConnectThread();
            connectThread.run();
            pool.add(connectThread);
        }
        ArrayBlockingQueue<Object> objects = new ArrayBlockingQueue<>(1);
        try {
            objects.put(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
