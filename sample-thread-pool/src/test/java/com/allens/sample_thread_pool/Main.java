package com.allens.sample_thread_pool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

    }

    @Test
    public void testThreadCacheThreadPool(){
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world in " + Thread.currentThread().getName());
            }
        });
    }
}
