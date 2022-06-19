package algorithms.warmup.plus_minus;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'plusMinus' function below.
	 *
	 * The function accepts INTEGER_ARRAY arr as parameter.
	 */

	public static void plusMinus(List<Integer> arr) {
		double positives = 0;
		double negatives = 0;
		double zeroes = 0;

		for (int value : arr) {
			if (value > 0)
				positives++;
			if (value < 0)
				negatives++;
			if (value == 0)
				zeroes++;
		}

		DecimalFormat decimalFormat = new DecimalFormat("0.000000");
		System.out.println(decimalFormat.format(positives / arr.size()));
		System.out.println(decimalFormat.format(negatives / arr.size()));
		System.out.println(decimalFormat.format(zeroes / arr.size()));
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		Result.plusMinus(arr);

		bufferedReader.close();
	}
}
