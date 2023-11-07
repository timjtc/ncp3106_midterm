package com.uecpe20231122784.lib;

import java.util.Random;

public class rand {
    
    private static Random r = new Random();

    public static int intByRange(int min, int max) {
        int rand = r.nextInt((max - min) + 1) + min;
        return rand;
    }
    
}