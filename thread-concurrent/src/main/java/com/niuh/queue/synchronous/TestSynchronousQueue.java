package com.niuh.queue.synchronous;


import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class TestSynchronousQueue {

    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread t1 = new Thread(consumer);
        Thread t2 = new Thread(producer);
        t1.start();
        t2.start();
    }

}

/**
 * 模拟生产者
 */
class Producer implements Runnable {

    SynchronousQueue<Integer> queue = null;


    public Producer(SynchronousQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int rand = new Random().nextInt(1000);
        System.out.println(String.format("模拟生产者：%d", rand));

        try {
            TimeUnit.SECONDS.sleep(3);
            queue.put(rand);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(queue.isEmpty());
    }
}

/**
 * 模拟消费者
 */
class Consumer implements Runnable {

    SynchronousQueue<Integer> queue = null;

    public Consumer(SynchronousQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("消费者已经准备好接收元素了...");
        try {
            System.out.println(String.format("消费一个元素：%d", queue.take()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("================================================");
    }
}
