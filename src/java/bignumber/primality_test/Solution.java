package java.bignumber.primality_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String n = bufferedReader.readLine();

		BigInteger bigInteger = new BigInteger(n);

		if (bigInteger.isProbablePrime(90))
			System.out.println("prime");
		else
			System.out.println("not prime");

		bufferedReader.close();
	}
}
