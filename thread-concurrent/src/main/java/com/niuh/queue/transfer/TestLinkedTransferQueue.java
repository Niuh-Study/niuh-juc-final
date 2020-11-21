package com.niuh.queue.transfer;


import java.util.Random;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class TestLinkedTransferQueue {
    public static void main(String[] args) {
        TransferQueue<String> queue = new LinkedTransferQueue<String>();
        Thread producer = new Thread(new Producer(queue));
        producer.setDaemon(true); // 设置为守护进程使得线程执行结束后程序自动结束运行
        producer.start();
        for (int i = 0; i < 10; i++) {
            Thread consumer = new Thread(new Consumer(queue));
            consumer.setDaemon(true);
            consumer.start();
            try {
                // 消费者进程休眠一秒钟，以便以便生产者获得CPU，从而生产产品
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 模拟生产者
 */
class Producer implements Runnable {
    private final TransferQueue<String> queue;

    public Producer(TransferQueue<String> queue) {
        this.queue = queue;
    }

    private String produce() {
        return " number " + (new Random().nextInt(100));
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (queue.hasWaitingConsumer()) {
                    queue.transfer(produce());
                }
                TimeUnit.SECONDS.sleep(1);// 生产者睡眠一秒钟,这样可以看出程序的执行过程
            }
        } catch (InterruptedException e) {
        }
    }
}

/**
 * 模拟消费者
 */
class Consumer implements Runnable {
    private final TransferQueue<String> queue;

    public Consumer(TransferQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println(" Consumer " + Thread.currentThread().getName() + queue.take());
        } catch (InterruptedException e) {
        }
    }
}