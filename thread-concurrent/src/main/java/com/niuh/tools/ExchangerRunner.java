package com.niuh.tools;

import org.apache.commons.lang.RandomStringUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * Exchanger示例
 * </p>
 */
public class ExchangerRunner {

    public static void main(String[] args) throws InterruptedException {
        ExchangerRunner.test2();
    }


    private static void test1() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() ->  {

                try {
                    String origMsg = RandomStringUtils.randomNumeric(6);

                    // 先到达的线程会在此等待，直到有一个线程跟它交换数据或者等待超时
                    String exchangeMsg = exchanger.exchange(origMsg,5, TimeUnit.SECONDS);

                    System.out.println(Thread.currentThread().getName() + "\t origMsg:" + origMsg + "\t exchangeMsg:" + exchangeMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }

            },String.valueOf(i)).start();
        }

        countDownLatch.await();
    }

    private static void test2() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        CountDownLatch countDownLatch = new CountDownLatch(4);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

        // 生产者
        Runnable producer = new Runnable() {
            @Override
            public void run() {
                try{
                    cyclicBarrier.await();

                    for (int i = 0; i < 5; i++) {
                        String msg = RandomStringUtils.randomNumeric(6);
                        exchanger.exchange(msg,5,TimeUnit.SECONDS);
                        System.out.println(Thread.currentThread().getName() + "\t producer msg -> " + msg + " ,\t i -> " + i);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            }
        };

        // 消费者
        Runnable consumer = new Runnable() {
            @Override
            public void run() {
                try{
                    cyclicBarrier.await();
                    for (int i = 0; i < 5; i++) {
                        String msg = exchanger.exchange(null,5,TimeUnit.SECONDS);
                        System.out.println(Thread.currentThread().getName() + "\t consumer msg -> " + msg + ",\t" + i);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            }
        };

        for (int i = 0; i < 2; i++){
            new Thread(producer).start();
            new Thread(consumer).start();
        }

        countDownLatch.await();
    }
}
