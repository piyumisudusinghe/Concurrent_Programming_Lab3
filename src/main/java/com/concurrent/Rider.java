package com.concurrent;

public class Rider implements Runnable{


    public void run(){

        try {
            Main.mutex.acquire();
            Main.waiting += 1;
            Main.mutex.release();

            Main.bus.wait();
            board();
            Main.boarded.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void board(){

    }
}




