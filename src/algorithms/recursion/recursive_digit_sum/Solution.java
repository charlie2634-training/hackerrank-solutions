package algorithms.recursion.recursive_digit_sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Result {

	public static int superDigit(String n, int k) {
		BigInteger totalValue = BigInteger.valueOf(0);

		for (int i = 0; i < n.length(); i++) {
			totalValue = totalValue.add(BigInteger.valueOf(Long.parseLong(String.valueOf(n.charAt(i))) * k));
		}

		if (totalValue.divide(BigInteger.valueOf(10)).intValue() == 0) {
			return totalValue.intValue();
		} else {
			return superDigit(totalValue.toString(), 1);
		}
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		String n = firstMultipleInput[0];

		int k = Integer.parseInt(firstMultipleInput[1]);

		int result = Result.superDigit(n, k);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
