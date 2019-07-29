package com.concurrent;
import java.util.concurrent.Semaphore;
import org.apache.commons.math3.distribution.ExponentialDistribution;

public class Main {

    static int waiting = 0;
    static Semaphore mutex = new Semaphore(1);
    static Semaphore bus = new Semaphore(0);
    static Semaphore boarded = new Semaphore(0);


    static ExponentialDistribution bus_arrival_time;
    static ExponentialDistribution rider_arrival_time;

    public static  void main(String args[]){

        System.out.println("main thread is running");

        //creating the exponential distribution for bus and riders arrival time
        bus_arrival_time = new ExponentialDistribution(20*60*1000);
        rider_arrival_time = new ExponentialDistribution(30*20*1000);

        //thread creates bus instances such that arrival time is in the exponential distribution
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    try {
                        Thread.sleep((long)bus_arrival_time.sample());
                        new Thread(new Bus()).start();
                    } catch (InterruptedException e) {
                        System.out.println("Exception:" + e);
                    }

                }

            }
        }

        ).start();

        //thread creates rider instances such that arrival time is in the exponential distribution
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    try {
                        Thread.sleep((long)bus_arrival_time.sample());
                        new Thread(new Rider()).start();
                    } catch (InterruptedException e) {
                        System.out.println("Exception:" + e);
                    }

                }
            }
        }

        ).start();









    }


}
