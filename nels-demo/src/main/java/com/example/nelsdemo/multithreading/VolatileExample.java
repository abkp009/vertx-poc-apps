package com.example.nelsdemo.multithreading;

import java.util.Scanner;

// all threads should see the latest value of a variable - volatile
// threads should not see the cached value of variable due to code optimization,
// even when other thread updates the variable
class Processor implements Runnable {
    //kind of shared variable between Main and Processor threads
    private volatile boolean running = true;

    public void stop() {
        this.running = false;
    }

    //it is not modifying running variable, so it thinks it's safe to cache it,
    // and it may ignore the latest value
    @Override
    public void run() {
        while (running) {
            System.out.println("Hello");
        }
    }
}

public class VolatileExample {
    public static void main(String[] args) {
        Processor proc = new Processor();
        new Thread(proc).start();
        System.out.println("Stop the thread...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        proc.stop();
    }
}
