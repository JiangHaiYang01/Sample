package com.allens.sample_thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
       Runnable runnable = () -> System.out.println("run");

        Executor executor = Executors.newCachedThreadPool();

        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(4);

    }
}
