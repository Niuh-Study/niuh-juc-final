package com.niuh.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * volatile 可见性
 * @description: -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*Jmm03_CodeVisibility.refresh
 * -Djava.compiler=NONE
 **/
@Slf4j
public class Jmm03_CodeVisibility {

    private static boolean initFlag = false;

    private volatile static int counter = 0;

    public static void refresh() {
        log.info("refresh data.......");
        initFlag = true;
        log.info("refresh data success.......");
    }

    public static void main(String[] args) {
        // 线程A
        Thread threadA = new Thread(() -> {
            while (!initFlag) {
                //System.out.println("runing");
                counter++;
            }
            log.info("线程：" + Thread.currentThread().getName()
                    + "当前线程嗅探到initFlag的状态的改变");
        }, "threadA");
        threadA.start();

        // 中间休眠500hs
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 线程B
        Thread threadB = new Thread(() -> {
            refresh();
        }, "threadB");
        threadB.start();
    }
}
