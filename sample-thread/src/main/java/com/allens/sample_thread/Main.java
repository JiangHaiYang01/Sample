package com.allens.sample_thread;

public class Main {
    public static void main(String[] args) {
        testDeadlock();
    }



    public static void testDeadlock() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + " 锁住obj1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName() + " 进不来的");
                    try {
                        Thread.sleep(1000) ;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        Thread thread2 = new Thread(() -> {
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + " 锁住obj2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName() + " 进不来的");
                    try {
                        Thread.sleep(1000) ;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
