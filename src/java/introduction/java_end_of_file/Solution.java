package java.introduction.java_end_of_file;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        
        while(scanner.hasNextLine())
        {
            counter++;
            String data = scanner.nextLine();
            System.out.printf("%d %s\n", counter, data);
        }
        
        scanner.close();
    }
}
