package com.niuh.jmm.dcl;

public class Singleton {

    /**
     * 查看汇编指令
     * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
     */
    private volatile static Singleton myinstance;

    /**
     * 双重锁机制保证单例安全
     *
     * @return
     */
    public static Singleton getInstance() {
        // 第一次检测
        if (myinstance == null) {
            // 同步
            synchronized (Singleton.class) {
                if (myinstance == null) {
                    // 不加 volatile 在多线程环境下可能会出现问题的地方
                    myinstance = new Singleton();
                }
            }
        }
        return myinstance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
