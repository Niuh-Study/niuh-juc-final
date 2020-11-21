package com.niuh;

/**
 * 同步类方法，锁是当前类对象
 * synchronized 修饰静态方法 锁定的是类本身,而不是实例,  同一个类中的所有被synchronized修饰的静态方法, 只能多线程同时调用一个
 **/
public class Juc_LockOnClass {
    static int stock;

    public static synchronized void decrStock(){
        System.out.println(--stock);
    }

    public static synchronized void cgg(){
        System.out.println();
    }

    public static void main(String[] args) {
        //Juc_LockOnClass.class对象
        Juc_LockOnClass.decrStock();
    }

}
