package com.allens.sample_thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;

public class MainTest {
    @Test
    public void testNew() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " run...");
        }, "t1");
        System.out.println("线程name is " + thread.getName() + "; status is " + thread.getState().name());
        Thread.sleep(1000);
        thread.start();
        System.out.println("线程name is " + thread.getName() + "; status is " + thread.getState().name());
        Thread.sleep(1000);
        System.out.println("线程name is " + thread.getName() + "; status is " + thread.getState().name());
    }

    @Test
    public void testBlocked() throws InterruptedException {
        Object block = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (block) {
                System.out.println(Thread.currentThread().getName() + " run...");
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        thread1.start();
        Thread.sleep(1000);
        Thread thread2 = new Thread(() -> {
            synchronized (block) {
                System.out.println(Thread.currentThread().getName() + " run...");
            }
        }, "t2");
        thread2.start();
        System.out.println("线程name is " + thread2.getName() + "; status is " + thread2.getState().name());

    }

    @Test
    public void testWaitIng() throws InterruptedException {
        AtomicInteger data = new AtomicInteger(100);
        Thread thread1 = new Thread(() -> {
            synchronized (data) {
                while (data.get() <= 200) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait...");
                        data.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " run...");
            }
        }, "t1");
        thread1.start();
        Thread.sleep(1000);
        System.out.println("线程name is " + thread1.getName() + "; status is " + thread1.getState().name());
        Thread thread2 = new Thread(() -> {
            synchronized (data) {
                data.set(300);
                System.out.println(Thread.currentThread().getName() + " notify...");
                data.notifyAll();
            }
        }, "t2");
        thread2.start();
    }


    @Test
    public void testStop() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " run...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
        }, "t1");
        thread.start();
        Thread.sleep(200);
        thread.stop();
    }

    @Test
    public void testDestroy() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " run...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
        }, "t1");
        thread.start();
        Thread.sleep(200);
        thread.destroy();
    }

    @Test
    public void testInterrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1_00_00; i++) {
                if (Thread.interrupted()) {
                    System.out.println("interrupted");
                    break;
                }
                System.out.println(i);
            }
        }, "t1");
        thread.start();
        Thread.sleep(5);
        thread.interrupt();

    }

    @Test
    public void testInterruptError() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " run...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
        }, "t1");
        thread.start();
        Thread.sleep(1000);
        System.out.println("status:" + thread.getState());
        thread.interrupt();
    }

    @Test
    public void testJoin() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " run...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
        }, "t1");
        thread.start();
//        thread.join();
        System.out.println(Thread.currentThread().getName() + " finish");

    }

//    @Test
//    public void testRun() {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + " run...");
//            }
//        };
//        Thread t1 = new Thread(runnable, "t1");
//        Thread t2 = new Thread(runnable, "t2");
//        t1.start();
//        t2.start();
//    }

    @Test
    public void testCustomThread() {
        new MyThread().start();
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println(Thread.currentThread().getName() + " run...");
        }
    }

    @Test
    public void testWaitAndSleep() throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " run...");
                try {
                    obj.wait(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finish");
            }
        }, "t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " run...");
                try {
                    obj.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finish");
            }
        }, "t2");
        t2.start();
        System.out.println(t1.getName() + " status:" + t1.getState().name());
        System.out.println(t2.getName() + " status:" + t2.getState().name());
    }


    @Test
    public void testYield() {
        Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " run " + i);
                if (i == 2) {
                    System.out.println(Thread.currentThread().getName() + " prepare yield");
                    Thread.yield();
                }
            }
        };
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(runnable, "t" + i);
            thread.start();
        }
    }





    @Test
    public void testDeadlock() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + " obj1 run...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println(Thread.currentThread().getName() + " obj2 run...");
                    try {
                        Thread.sleep(1000 * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        Thread thread2 = new Thread(() -> {
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + " obj2 run...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println(Thread.currentThread().getName() + " obj1 run...");
                    try {
                        Thread.sleep(1000 * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2");
        thread1.start();
        thread2.start();
    }

    @Test
    public void testRun(){
        Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run...");
            }
        },"t1");
        t.start();
    }
}
