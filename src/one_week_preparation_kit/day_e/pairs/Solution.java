package one_week_preparation_kit.day_e.pairs;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'pairs' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * following parameters: 1. INTEGER k 2. INTEGER_ARRAY arr
	 */

	public static int pairs(int k, List<Integer> arr) {
		Map<Integer, Integer> valueCount = new HashMap<>();

		for (int i = 0; i < arr.size(); i++) {
			valueCount.putIfAbsent(arr.get(i), 0);
			valueCount.put(arr.get(i), valueCount.get(arr.get(i)) + 1);
		}

		int count = 0;

		for (int i = 0; i < arr.size(); i++) {
			int necessaryValue = arr.get(i) - k;
			count += valueCount.getOrDefault(necessaryValue, 0);
		}

		return count;
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = Result.pairs(k, arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
