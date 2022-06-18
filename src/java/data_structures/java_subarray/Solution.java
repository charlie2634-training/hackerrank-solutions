package java.data_structures.java_subarray;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int numberOfElements = scanner.nextInt();
		int[] elements = new int[numberOfElements];

		for (int i = 0; i < numberOfElements; i++) {
			elements[i] = scanner.nextInt();
		}

		int count = 0;

		for (int i = 0; i < numberOfElements; i++) {
			int sum = 0;

			for (int j = i; j < numberOfElements; j++) {
				sum += elements[j];

				if (sum < 0)
					count++;
			}
		}
		scanner.close();
		System.out.println(count);
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
	}
}
