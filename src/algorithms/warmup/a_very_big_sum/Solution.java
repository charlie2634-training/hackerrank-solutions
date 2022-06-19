package algorithms.warmup.a_very_big_sum;

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
	 * Complete the 'aVeryBigSum' function below.
	 *
	 * The function is expected to return a LONG_INTEGER. The function accepts
	 * LONG_INTEGER_ARRAY ar as parameter.
	 */

	public static long aVeryBigSum(List<Long> ar) {
		return ar.stream().reduce((x, y) -> x + y).get();
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		Integer.parseInt(bufferedReader.readLine().trim());

		List<Long> ar = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Long::parseLong)
				.collect(toList());

		long result = Result.aVeryBigSum(ar);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
