package com.concurrent;

public class Rider implements Runnable{


    public void run(){

        try {
            Main.mutex.acquire();
            Main.waiting += 1;
            System.out.println("Rider - "+ Long.toString(Thread.currentThread().getId()) + " came to the bus halt");
            Main.mutex.release();

            Main.bus.acquire();
            board();
            Main.boarded.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void board(){
        System.out.println("Rider - " + Long.toString(Thread.currentThread().getId())+  " boarded to the bus");
    }
}




