package com.niuh.jmm;

public class Jmm06_MemoryBarrier {
    int a;
    public volatile int m1 = 1;
    public volatile int m2 = 2;

    public void readAndWrite() {
        int i = m1;   // 第一个volatile读
        int j = m2;   // 第二个volatile读

        a = i + j;    // 普通写

        m1 = i + 1;   // 第一个volatile写
        m2 = j * 2;   // 第二个 volatile写
    }

}
