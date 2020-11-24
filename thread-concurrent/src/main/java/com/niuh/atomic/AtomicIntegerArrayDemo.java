package com.niuh.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * <p>
 * AtomicIntArrayDemo示例
 * </p>
 */
public class AtomicIntegerArrayDemo {
    // 申明了一个内含10个元素的数组
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    // 定义的线程对数组内10个元素进行累加操作，每个元素各加1000次
    public static class AddThread implements Runnable {
        public void run() {
            for (int k = 0; k < 10000; k++) {
                arr.getAndIncrement(k % arr.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) {
            ts[k].start();
        }
        for (int k = 0; k < 10; k++) {
            ts[k].join();
        }
        System.out.println(arr);
    }
}
