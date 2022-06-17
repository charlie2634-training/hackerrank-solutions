package bignumber.java_biginteger;

import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */

		Scanner scanner = new Scanner(System.in);
		String firstNumber = scanner.nextLine();
		String secondNumber = scanner.nextLine();

		BigInteger firstBigInteger = new BigInteger(firstNumber);
		BigInteger secondBigInteger = new BigInteger(secondNumber);

		System.out.println(firstBigInteger.add(secondBigInteger));
		System.out.println(firstBigInteger.multiply(secondBigInteger));
		scanner.close();
	}
}
