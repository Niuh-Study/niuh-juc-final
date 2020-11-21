package com.niuh.queue.linked;

import org.apache.commons.lang.RandomStringUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TestLinkedBlockingQueue {

    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
    // 线程控制开关
    private final CountDownLatch latch = new CountDownLatch(1);
    // 线程池
    private final ExecutorService pool;
    // AtomicLong 计数 生产数量
    private final AtomicLong output = new AtomicLong(0);
    // AtomicLong 计数  销售数量
    private final AtomicLong sales = new AtomicLong(0);
    // 是否停止线程
    private final boolean clear;

    public TestLinkedBlockingQueue(boolean clear) {
        this.pool = Executors.newCachedThreadPool();
        this.clear = clear;
    }

    public void service() throws InterruptedException {
        Consumer a = new Consumer(queue, sales, latch, clear);
        pool.submit(a);

        Producer w = new Producer(queue, output, latch);
        pool.submit(w);
        latch.countDown();
    }

    public static void main(String[] args) {
        TestLinkedBlockingQueue t = new TestLinkedBlockingQueue(false);
        try {
            t.service();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者（销售产品）
 */
class Consumer implements Runnable {
    private final LinkedBlockingQueue<String> queue;
    private final AtomicLong sales;
    private final CountDownLatch latch;
    private final boolean clear;

    public Consumer(LinkedBlockingQueue<String> queue, AtomicLong sales, CountDownLatch latch, boolean clear) {
        this.queue = queue;
        this.sales = sales;
        this.latch = latch;
        this.clear = clear;
    }

    public void run() {
        try {
            latch.await(); // 放闸之前老实的等待着
            for (; ; ) {
                sale();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            if (clear) { // 响应中断请求后,如果有要求则销售完队列的产品后再终止线程
                cleanWarehouse();
            } else {
                System.out.println("Seller Thread will be interrupted...");
            }
        }
    }

    public void sale() {
        System.out.println("==取take=");
        try {
            String item = queue.poll(50, TimeUnit.MILLISECONDS);
            System.out.println(item);
            if (item != null) {
                sales.incrementAndGet(); // 可以声明long型的参数获得返回值,作为日志的参数
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 销售完队列剩余的产品
     */
    private void cleanWarehouse() {
        try {
            while (queue.size() > 0) {
                sale();
            }
        } catch (Exception ex) {
            System.out.println("Seller Thread will be interrupted...");
        }
    }
}

/**
 * 生产者（生产产品）
 *
 */
class Producer implements Runnable {
    private LinkedBlockingQueue<String> queue;
    private CountDownLatch latch;
    private AtomicLong output;

    public Producer() {

    }

    public Producer(LinkedBlockingQueue<String> queue, AtomicLong output, CountDownLatch latch) {
        this.queue = queue;
        this.latch = latch;
        this.output = output;
    }

    public void run() {
        try {
            latch.await(); // 线程等待
            for (; ; ) {
                work();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer thread will be interrupted...");
        }
    }

    /**
     * 工作
     */
    public void work() {
        try {
            String product = RandomStringUtils.randomAscii(3);
            boolean success = queue.offer(product, 100, TimeUnit.MILLISECONDS);
            if (success) {
                output.incrementAndGet();// 可以声明long型的参数获得返回值,作为日志的参数
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
