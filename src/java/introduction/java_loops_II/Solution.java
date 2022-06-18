package java.introduction.java_loops_II;

import java.util.Scanner;

class Solution {
	public static void main(String[] argh) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int n = in.nextInt();
			printSeries(a, b, n);
		}
		in.close();
	}

	private static void printSeries(int a, int b, int n) {
		int currentValue = a;

		for (int i = 0; i < n; i++) {
			Double pow = Math.pow(2, i);
			currentValue = currentValue + (pow.intValue() * b);
			System.out.printf("%d ", currentValue);
		}
		System.out.println();
	}
}
