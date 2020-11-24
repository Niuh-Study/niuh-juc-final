package com.niuh.deque;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/*
 *   LinkedBlockingDeque是“线程安全”的队列，而LinkedList是非线程安全的。
 *
 *   下面是“多个线程同时操作并且遍历queue”的示例
 *   (1) 当queue是LinkedBlockingDeque对象时，程序能正常运行。
 *   (2) 当queue是LinkedList对象时，程序会产生ConcurrentModificationException异常。
 *
 */
public class LinkedBlockingDequeRunner1 {

    // TODO: queue是LinkedList对象时，程序会出错。
    // private static Queue<String> queue = new LinkedList<String>();
    private static Queue<String> queue = new LinkedBlockingDeque<String>();

    public static void main(String[] args) {

        // 同时启动两个线程对queue进行操作！
        new MyThread("A").start();
        new MyThread("B").start();
    }

    private static void printAll() {
        String value;
        Iterator iter = queue.iterator();
        while (iter.hasNext()) {
            value = (String) iter.next();
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                // “线程名” + "-" + "序号"
                String val = Thread.currentThread().getName() + i;
                queue.add(val);
                // 通过“Iterator”遍历queue。
                printAll();
            }
        }
    }
}