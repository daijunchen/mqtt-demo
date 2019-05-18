package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void test() throws InterruptedException {

    }

    @Test
    public void test2() throws InterruptedException {
        int count = 1000;
        ArrayList<ThreadBuilder> pool = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ThreadBuilder threadBuilder = new ThreadBuilder();
            threadBuilder.run();
            pool.add(threadBuilder);
        }
        ArrayBlockingQueue<Object> objects = new ArrayBlockingQueue<>(1);
        objects.put(this);
    }
}
