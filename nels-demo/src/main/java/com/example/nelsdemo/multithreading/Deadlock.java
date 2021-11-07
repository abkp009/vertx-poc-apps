package com.example.nelsdemo.multithreading;

// Cause of deadlock
// Nested sync blocks or methods
// different lock order
// solution - same lock order, avoid nested sync, tryLock() of Reentrant Lock
public class Deadlock {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void firstThread() {
        while (true) {
            synchronized (lock1) {
                synchronized (lock2) {
                    System.out.println("hello1");
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void secondThread() {
        while (true) {
            synchronized (lock2) {
                synchronized (lock1) {
                    System.out.println("hello2");
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Deadlock pc = new Deadlock();
        Thread t1 = new Thread(pc::firstThread);
        Thread t2 = new Thread(pc::secondThread);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
