package algorithms.implementation.bill_division;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'bonAppetit' function below.
	 *
	 * The function accepts following parameters: 1. INTEGER_ARRAY bill 2.
	 * INTEGER k 3. INTEGER b
	 */

	public static void bonAppetit(List<Integer> bill, int k, int b) {
		bill.remove(k);
		int expectedValue = bill.stream().mapToInt(x -> x.intValue()).sum() / 2;

		if (expectedValue == b) {
			System.out.println("Bon Appetit");
		} else {
			System.out.println(b - expectedValue);
		}
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> bill = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int b = Integer.parseInt(bufferedReader.readLine().trim());

		Result.bonAppetit(bill, k, b);

		bufferedReader.close();
	}
}
