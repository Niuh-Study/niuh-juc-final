package com.niuh.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Semaphore示例
 * </p>
 */
public class SemaphoreRunner {
    /**
     * 线程数量
     */
    private static final int THREAD_COUNT = 30;

    /**
     * 线程池
     */
    private static ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        // 获取一个"许可证"
                        semaphore.acquire();

                        // 模拟数据保存
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("save date...");

                        // 执行完后,归还"许可证"
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
    }
}
