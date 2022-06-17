package strings.java_substring;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String myString = in.next();
		int start = in.nextInt();
		int end = in.nextInt();
		in.close();
		System.out.println(myString.substring(start, end));
	}
}
