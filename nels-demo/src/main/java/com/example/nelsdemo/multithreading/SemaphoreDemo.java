package com.example.nelsdemo.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreDemo {
    private Semaphore semaphore = new Semaphore(2);// only 2 resource can exist within Sem boundary
    private static int count = 0;

    private SemaphoreDemo() {

    }

    private SemaphoreDemo getInstance() {
        System.out.println(count++);
        return new SemaphoreDemo();
    }

    public void get() {
        try {
            semaphore.acquire();
            getInstance();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            count--;
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo sem = new SemaphoreDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 20).forEach(value -> executorService.submit(sem::get));
    }

}
