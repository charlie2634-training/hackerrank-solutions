package java.introduction.java_stdin_and_stdout_I;

import java.util.*;

public class Solution {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        scanAndPrintNumber();
        scanAndPrintNumber();
        scanAndPrintNumber();
    }
    
    private static void scanAndPrintNumber() {
        int a = scan.nextInt();
        System.out.println(a);
    }
}
