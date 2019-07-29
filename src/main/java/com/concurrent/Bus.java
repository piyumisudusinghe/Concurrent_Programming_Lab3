package com.concurrent;
import java.math.*;

public class Bus implements Runnable{

    public int n;

    public void run(){
        try {
            Main.mutex.acquire();
            System.out.println("Bus - " + Long.toString(Thread.currentThread().getId()) + " is arrived");
            n = Math.min(Main.waiting,50);
            for(int i =0; i < n ;i++){
                Main.bus.release();
                Main.boarded.release();
            }

            Main.waiting = Math.max(Main.waiting-50,0);
            Main.mutex.release();

            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void depart(){
        System.out.println("Bus - " + Long.toString(Thread.currentThread().getId())+  " is leaving");
    }
}
