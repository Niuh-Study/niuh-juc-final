package com.niuh.jmm;

/**
 * volatile可以保证可见性, 不能保证原子性
 */
public class Jmm04_CodeAtomic {

    private volatile static int counter = 0;
    static Object object = new Object();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    synchronized (object) {
                        counter++;//分三步- 读，自加，写回
                    }
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);

    }
}
