package com.example.nelsdemo.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

// as there are two resources, so we can use 2 different object lock for better performance
// don't use list object for locking as it is changing, we can't risk it.
public class SharedResourceIssue {
    private Random random = new Random();
    private List<Integer> resource1List = new ArrayList<>();
    private List<Integer> resource2List = new ArrayList<>();
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private void addToNum1() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource1List.add(random.nextInt());
        }
    }

    private void addToNum2() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource2List.add(random.nextInt());
        }
    }

    public void add() {
        addToNum1();
        addToNum2();
    }

    public static void main(String[] args) {
        SharedResourceIssue res = new SharedResourceIssue();
        Thread t1 = new Thread(() -> runBody(res));
        Thread t2 = new Thread(() -> runBody(res));
        long start = System.currentTimeMillis();
        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("numbers1 " + res.resource1List.size());
        System.out.println("numbers2 " + res.resource2List.size());
        System.out.println("Total time " + (System.currentTimeMillis() - start));
    }

    public static void runBody(SharedResourceIssue res) {
        IntStream.range(0, 100).forEach(i -> res.add());
    }
}
