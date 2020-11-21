package com.niuh;

public class Juc03_Thread_Synchronize {

    private final static Object object = new Object();

    public static void reentrantlock(){
        String tname = Thread.currentThread().getName();
        synchronized (object) {
            System.out.println(String.format("{}:) hold {}->monitor lock",tname,object));
            synchronized (object){
                System.out.println(String.format("{}:) re-hold {}->monitor lock",tname,object));
            }
        }
    }

    public static void main(String[] args)
    {
        Juc03_Thread_Synchronize.reentrantlock();
    }
}
