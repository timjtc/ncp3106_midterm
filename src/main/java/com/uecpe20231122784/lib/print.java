package com.uecpe20231122784.lib;

public class print {
    
    public static <PrintableToString> void ln(PrintableToString... args) {
        for (PrintableToString pts : args) {
            System.out.print(pts);
        }
        System.out.println();
    }

    public static <PrintableToString> void s(PrintableToString... args) {
        for(PrintableToString pts: args) {
            System.out.print(pts);
        }
            
    }

    public static <PrintableToString> void f(String format, PrintableToString... args) {
        System.out.println(String.format(format, args));
    }
    
}