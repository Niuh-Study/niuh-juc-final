package com.niuh;

import org.openjdk.jol.info.ClassLayout;

/**
 * 同步实例方法，锁是当前实例对象
 * synchronized 修饰非静态方法    锁定的是该bai类的实例du  同一实例在多线程中调用才会触发同步锁定  所以  多个被synchronized修饰的非静态方法在同一实例下  只能多线程同时调用一个
 **/
public class Juc_LockOnThisObject {

    private Integer stock = 10;

    public synchronized void decrStock() {
        --stock;
        System.out.println(ClassLayout.parseInstance(this).toPrintable());
    }

    public static void main(String[] args) {
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Juc_LockOnThisObject to = new Juc_LockOnThisObject();
        //System.out.println(ClassLayout.parseInstance(to).toPrintable());
        to.decrStock();

        Juc_LockOnThisObject to1 = new Juc_LockOnThisObject();
        to1.decrStock();
    }
}
