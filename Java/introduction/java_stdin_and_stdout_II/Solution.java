package introduction.java_stdin_and_stdout_II;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int myInt = scan.nextInt();
        double myDouble = scan.nextDouble();
        String myString = scan.nextLine();
        myString = scan.nextLine();
        System.out.println("String: " + myString);
        System.out.println("Double: " + myDouble);
        System.out.println("Int: " + myInt);
    }
}
