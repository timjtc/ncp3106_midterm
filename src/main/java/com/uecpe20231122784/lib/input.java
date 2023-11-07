package com.uecpe20231122784.lib;

import java.util.Scanner;

public class input {

    public static Scanner s = new Scanner(System.in);
    
    public static Object wait(String type) {
        String i = s.nextLine();
        switch (type) {
            case "int":
                return Integer.parseInt(i);
            case "double":
                return Double.parseDouble(i);
            case "float":
                return Float.parseFloat(i);
            case "boolean":
                return Boolean.parseBoolean(i);
            default:
                return i;
        }
    }

    public static char schar() {
        return s.next(".").charAt(0);
    }

}