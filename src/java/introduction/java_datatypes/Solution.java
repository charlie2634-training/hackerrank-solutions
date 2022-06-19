package java.introduction.java_datatypes;

import java.util.Scanner;

class Solution {
	public static void main(String[] argh) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();

		for (int i = 0; i < t; i++) {
			try {
				long value = scanner.nextLong();
				printMatchableDataType(value);
			} catch (Exception e) {
				System.out.println(scanner.next() + " can't be fitted anywhere.");
			}
		}
		scanner.close();
	}

	private static void printMatchableDataType(long value) {
		System.out.println(value + " can be fitted in:");

		// Be careful, MAX_VALUE != -MIN_VALUE.

		if (Byte.MIN_VALUE <= value && value <= Byte.MAX_VALUE)
			System.out.println("* byte");
		if (Short.MIN_VALUE <= value && value <= Short.MAX_VALUE)
			System.out.println("* short");
		if (Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE)
			System.out.println("* int");
		if (Long.MIN_VALUE <= value && value <= Long.MAX_VALUE)
			System.out.println("* long");
	}
}
