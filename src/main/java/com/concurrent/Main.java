package com.concurrent;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import org.apache.commons.math3.distribution.ExponentialDistribution;

public class Main {

    static int waiting = 0;
    static Semaphore mutex = new Semaphore(1);
    static Semaphore bus = new Semaphore(0);
    static Semaphore boarded = new Semaphore(0);


    static ExponentialDistribution bus_arrival_time;
    static ExponentialDistribution rider_arrival_time;

    static Scanner sc = new Scanner(System.in);
    static double bus_mean;
    static double rider_mean;

    public static  void main(String[] args){

        System.out.println("main thread is running");

        System.out.print("Enter the mean time for the Bus arrival time(minutes) : ");
        bus_mean = sc.nextDouble();


        System.out.print("Enter the mean time for the Rider arrival time(minutes) : ");
        rider_mean = sc.nextDouble();

        //creating the exponential distribution for bus and riders arrival time
        bus_arrival_time = new ExponentialDistribution(bus_mean*60*1000);
        rider_arrival_time = new ExponentialDistribution(rider_mean*60*1000);

        //thread creates bus instances such that arrival time is in the exponential distribution
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    try {
                        Thread.sleep((long)Main.bus_arrival_time.sample());
                        new Thread(new Bus()).start();
                       // System.out.println("created new bus");
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
                        Thread.sleep((long)Main.rider_arrival_time.sample());
                        new Thread(new Rider()).start();
                      //  System.out.println("created new rider");
                    } catch (InterruptedException e) {
                        System.out.println("Exception:" + e);
                    }

                }
            }
        }

        ).start();




    }


}
