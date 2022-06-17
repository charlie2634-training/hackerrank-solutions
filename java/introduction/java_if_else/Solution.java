package introduction.java_if_else;

import java.util.Scanner;

public class Solution {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int number = readInt();

		boolean isOdd = (number % 2 == 1);

		if (isOdd) {
			System.out.println("Weird");
		} else {
			if (2 <= number && number <= 5) {
				System.out.println("Not Weird");
			} else if (6 <= number && number <= 20) {
				System.out.println("Weird");
			} else if (number > 20) {
				System.out.println("Not Weird");
			}
		}
	}

	private static int readInt() {
		int number = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		scanner.close();
		return number;
	}
}
