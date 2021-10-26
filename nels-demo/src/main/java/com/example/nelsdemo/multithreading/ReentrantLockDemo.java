package com.example.nelsdemo.multithreading;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock is an alternative to synchronized ( Ex. lock.lock(),lock.unlock() )
// Condition is used to simulate wait and notify
// Condition - wait -> await, notify -> signal
public class ReentrantLockDemo {
    private final LinkedList<Integer> listResource = new LinkedList<>();
    private final Random random = new Random();
    private final int LIMIT = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void produce() {
        while (true) {
            try {
                lock.lock();
                while (listResource.size() == LIMIT) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                listResource.add(random.nextInt(100));
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void consume() {
        while (true) {
            try {
                lock.lock();
                while (listResource.isEmpty()) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("list size: " + listResource.size());
                int firstVal = listResource.removeFirst();
                System.out.println("value taken " + firstVal);
                condition.signal();
                Thread.sleep(1000);
                System.out.println("producer resumed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo pc = new ReentrantLockDemo();
        Thread t1 = new Thread(pc::produce);
        Thread t2 = new Thread(pc::consume);
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
