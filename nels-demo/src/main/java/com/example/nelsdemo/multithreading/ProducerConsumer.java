package com.example.nelsdemo.multithreading;

import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumer {
    private LinkedList<Integer> listResource = new LinkedList<>();
    private Random random = new Random();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() {
        while (true) {
            synchronized (lock) {
                while (listResource.size() == LIMIT) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                listResource.add(random.nextInt(100));
                lock.notify();
            }
        }
    }

    public void consume() {
        while (true) {
            synchronized (lock) {
                while (listResource.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("list size: " + listResource.size());
                int firstVal = listResource.removeFirst();
                System.out.println("value taken " + firstVal);
                lock.notify();
                System.out.println("producer resumed");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
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
