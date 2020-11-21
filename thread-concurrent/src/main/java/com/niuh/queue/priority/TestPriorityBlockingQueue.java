package com.niuh.queue.priority;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * <p>
 * PriorityBlockingQueue 简单演示 demo
 * </p>
 */
public class TestPriorityBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        // 大顶堆
        PriorityBlockingQueue<Integer> concurrentLinkedQueue = new PriorityBlockingQueue<Integer>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        concurrentLinkedQueue.offer(90);
        concurrentLinkedQueue.offer(80);
        concurrentLinkedQueue.offer(70);
        concurrentLinkedQueue.offer(60);
        concurrentLinkedQueue.offer(40);
        concurrentLinkedQueue.offer(30);
        concurrentLinkedQueue.offer(20);
        concurrentLinkedQueue.offer(10);
        concurrentLinkedQueue.offer(50);
        concurrentLinkedQueue.offer(85);
        //输出元素排列
        concurrentLinkedQueue.stream().forEach(e-> System.out.print(e+"  "));
        //取出元素
        Integer take = concurrentLinkedQueue.take();
        System.out.println();
        concurrentLinkedQueue.stream().forEach(e-> System.out.print(e+"  "));
    }
}
