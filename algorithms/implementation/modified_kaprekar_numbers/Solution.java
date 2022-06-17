package implementation.modified_kaprekar_numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Result {

	static class SquaredNumber {
		int number;
		long squared;
		String squaredConverted;

		public SquaredNumber(int number) {
			this.number = number;
			this.squared = (long) number * number;
			this.squaredConverted = String.valueOf(squared);
		}

		public int getNumber() {
			return number;
		}

		public long getSquared() {
			return squared;
		}

		public boolean isKaprekar() {
			String firstHalf = squaredConverted.substring(0, squaredConverted.length() / 2);
			String secondHalf = squaredConverted.substring(squaredConverted.length() / 2);

			int firstValue = firstHalf.isEmpty() ? 0 : Integer.valueOf(firstHalf);
			int secondValue = secondHalf.isEmpty() ? 0 : Integer.valueOf(secondHalf);

			return (firstValue + secondValue == number);
		}
	}

	public static void kaprekarNumbers(int p, int q) {
		List<SquaredNumber> result = IntStream.rangeClosed(p, q).mapToObj(x -> new SquaredNumber(x))
				.filter(x -> x.isKaprekar()).collect(Collectors.toList());

		if (result.isEmpty()) {
			System.out.println("INVALID RANGE");
		} else {
			result.forEach(x -> System.out.print(x.getNumber() + " "));
		}
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int p = Integer.parseInt(bufferedReader.readLine().trim());

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		Result.kaprekarNumbers(p, q);

		bufferedReader.close();
	}
}
