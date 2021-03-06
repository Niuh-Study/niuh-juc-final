package com.niuh;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class T0_MultiThreadAndUnsafe {

    private static int total = 0;
    private static Object object = new Object();
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                    for(int j=0;j<1000;j++){
                        try {
                            lock.lock();
                            //synchronized (object){
                            total++;
                            //}

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(1000);

        countDownLatch.countDown();

        Thread.sleep(2000);

        System.out.println(total);
    }
}
