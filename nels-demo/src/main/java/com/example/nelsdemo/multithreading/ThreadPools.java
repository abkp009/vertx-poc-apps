package com.example.nelsdemo.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPools {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 5).forEach(id -> executor.submit(() -> {
            System.out.println("Starting " + id);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completing " + id);
        }));

        try {
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Program Ends");
    }
}
