package com.niuh.deque;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <p>
 * LinkedBlockingQueue示例，生产者消费者
 * </p>
 */
public class LinkedBlockingQueueRunner {
    public static void main(String[] args) {
        BlockingQueue<Integer> shareQueue = new LinkedBlockingDeque<>();

        Producer P = new Producer(shareQueue);
        Consumer C = new Consumer(shareQueue);

        P.start();
        C.start();
    }
}

/**
 * 生产者
 */
class Producer extends Thread {
    private BlockingQueue<Integer> sharedQueue;

    public Producer(BlockingQueue<Integer> shareQueue) {
        super("PRODUCER");
        this.sharedQueue = shareQueue;
    }

    public void run() {
        //no synchronization needed
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(getName() + " produced " + i);
                sharedQueue.put(i);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {
    private BlockingQueue<Integer> shareQueue;

    public Consumer(BlockingQueue<Integer> shareQueue) {
        super("CONSUMER");
        this.shareQueue = shareQueue;
    }

    public void run() {
        try {
            while (true) {
                Integer item = shareQueue.take();
                System.out.println(getName() + " consumed " + item);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}