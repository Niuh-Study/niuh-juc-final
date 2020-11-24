package com.niuh.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * <p>
 * 本实例演示对变量的参数进行自增，让其安全的
 * </p>
 */
public class AtomicFieldUpdaterDemo implements Runnable {
    static Person personOne = new Person();
    static Person personTwo = new Person();
    AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");

    public static void main(String[] args) throws InterruptedException {
        AtomicFieldUpdaterDemo atomicFieldUpdaterDemo = new AtomicFieldUpdaterDemo();
        Thread thread1 = new Thread(atomicFieldUpdaterDemo);
        Thread thread2 = new Thread(atomicFieldUpdaterDemo);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("personOne age:" + personOne.age);
        System.out.println("personTwo age:" + personTwo.age);

    }


    @Override
    public void run() {
        //循环1000次
        for (int i = 0; i < 1000; i++) {
            personOne.age++;
            atomicIntegerFieldUpdater.getAndIncrement(personTwo);
        }
    }
}

class Person {
    volatile int age;
}
