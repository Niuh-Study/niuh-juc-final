package com.niuh.jmm;

import java.text.DecimalFormat;

/**
 * <p>
 * 每隔64Byte访问数组固定次数，看Array大小对耗时的影响
 * </p>
 */
public class Jmm01_cpucache {

    private static long marked;

    public static void main(String[] args) {
        for (int ARRAY_SIZE = 512; ARRAY_SIZE <= 128 * 1024 * 1024; ARRAY_SIZE <<= 1) {

            int steps = 640 * 1024 * 1024; // Arbitrary number of steps
            int length_mod = ARRAY_SIZE - 1;
            char[] arr = new char[ARRAY_SIZE];

            marked = System.currentTimeMillis();
            for (int i = 0; i < steps; i += 64) {
                arr[i & length_mod]++; // (i & length_mod) is equal to (i % length_mod)
            }
            long used = (System.currentTimeMillis() - marked);
            System.out.println(formatSize(ARRAY_SIZE) + "\t" + used);
        }
    }

    /**
     * 把size单位转化为KB, MB, GB
     */
    public static String formatSize(long size) {
        String hrSize = null;

        double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0");

        if (t > 1) {
            hrSize = dec.format(t).concat(" TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat(" GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat(" MB");
        } else if (k > 1) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }
        return hrSize;
    }
}

