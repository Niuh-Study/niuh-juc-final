package com.niuh.jmm;

/**
 * @description: -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*Jmm07_ByteCodeJitDump.refresh
 **/
public class Jmm07_ByteCodeJitDump {
    private static int c = 1;

    public static int refresh(){
        int a = 0;
        int b = 1;
        int sub = a + b + c;
        return sub;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread0 = new Thread(()->{
            System.out.println(String.format("sub0:%d",refresh()));
        });

        thread0.start();

        Thread thread1 = new Thread(()->{
            System.out.println(String.format("sub1:%d",refresh()));
        });

        thread1.start();
    }
}
