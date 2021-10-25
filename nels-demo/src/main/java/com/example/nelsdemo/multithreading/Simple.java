package com.example.nelsdemo.multithreading;

import java.util.stream.IntStream;

public class Simple {
    public static void main(String[] args) {
        System.out.println("Creating thread");
        //shortest way to create a thread
        //new Thread(() -> IntStream.range(1,10).forEach(System.out::println)).start();
        //new Thread(() -> IntStream.range(1,12).forEach(System.out::println)).start();
        new Thread(Simple::runSimulator).start();
        System.out.println("Main thread end");
    }
    public static void runSimulator(){
        IntStream.range(1,15).forEach(System.out::println);
    }
}
