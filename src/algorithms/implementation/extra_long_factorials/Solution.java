package algorithms.implementation.extra_long_factorials;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Result {

	/*
	 * Complete the 'extraLongFactorials' function below.
	 *
	 * The function accepts INTEGER n as parameter.
	 */

	public static void extraLongFactorials(int n) {
		BigInteger result = new BigInteger("1");

		for (long i = 1; i <= n; i++)
			result = result.multiply(new BigInteger(String.valueOf(i)));

		System.out.println(result.toString());
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		Result.extraLongFactorials(n);

		bufferedReader.close();
	}
}
