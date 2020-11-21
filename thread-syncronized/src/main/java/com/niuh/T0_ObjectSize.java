package com.niuh;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class T0_ObjectSize {

    public static void main(String[] args) throws InterruptedException {
        //TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
