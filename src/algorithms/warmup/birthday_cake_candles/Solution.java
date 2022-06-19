package algorithms.warmup.birthday_cake_candles;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'birthdayCakeCandles' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY candles as parameter.
	 */

	public static int birthdayCakeCandles(List<Integer> candles) {
		int count = 0;
		int maximum = 0;

		for (int value : candles) {
			if (value > maximum) {
				maximum = value;
				count = 1;
			} else if (value == maximum) {
				count++;
			}
		}

		return count;
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int candlesCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> candles = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = Result.birthdayCakeCandles(candles);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
