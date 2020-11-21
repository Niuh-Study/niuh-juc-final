package com.niuh.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 多线程生产者-消费者示例
 **/
public class Main {

    public static void main(String[] args) {
        int BOUND = 10;
        int N_PRODUCERS = 16;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors(); //=8
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS; // =0
        int mod = N_CONSUMERS % N_PRODUCERS;//0+8=8

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(BOUND);

        //潘金莲给武大郎熬药
        for (int i = 1; i < N_PRODUCERS; i++) {
            new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer)).start();
        }

        //武大郎开始喝药
        for (int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new NumbersConsumer(queue, poisonPill)).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //潘金莲开始投毒，武大郎喝完毒药GG
        new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer + mod)).start();
    }

}
