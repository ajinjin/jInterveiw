package com.gu.thread.concurrent.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for(int i = 0 ; i < 50 ; i++){
            fixedThreadPool.submit(new Thread( () -> {
                System.out.println("do something"+ Thread.currentThread().getName()+"  ");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        fixedThreadPool.shutdown();
    }

    public void testThreadPool(){
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for(int i = 0 ; i < 50 ; i++){
            singleThreadPool.submit(new Thread( () -> {
                System.out.println("do something"+ Thread.currentThread().getName()+"  ");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
    }



}
