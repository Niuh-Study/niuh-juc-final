package com.niuh;

import java.util.concurrent.locks.ReentrantLock;

public class Juc_LockAppend {

    StringBuffer stb = new StringBuffer();

    private void method() {
        stb.append("张三");
        stb.append("李四");
        stb.append("王五");
        stb.append("赵六");
    }

    /**
     * 开启逃逸分析：-server -XX:+DoEscapeAnalysis -XX:+EliminateLocks
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int size = 10000;
        for (int i = 0; i < size; i++) {
            createStringBuffer("一角钱技术", "为分享技术而生");
        }
        long timeCost = System.currentTimeMillis() - start;
        System.out.println("createStringBuffer:" + timeCost + " ms");
    }

    public static String createStringBuffer(String str1, String str2) {
        StringBuffer sBuf = new StringBuffer();
        sBuf.append(str1);// append方法是同步操作
        sBuf.append(str2);
        return sBuf.toString();
    }

}
