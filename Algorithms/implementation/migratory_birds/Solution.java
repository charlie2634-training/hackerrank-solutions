package implementation.migratory_birds;

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
	 * Complete the 'migratoryBirds' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */

	public static int migratoryBirds(List<Integer> arr) {
		Map<Integer, Integer> counters = new HashMap<>();
		int maxValue = 0;

		for (int i = 0; i < arr.size(); i++) {
			int key = arr.get(i);
			if (counters.get(key) == null)
				counters.put(key, 0);

			int newValue = counters.get(key) + 1;
			if (newValue > maxValue)
				maxValue = newValue;
			counters.put(key, newValue);
		}

		System.out.println(maxValue);

		final int finalMaxValue = maxValue;

		return counters.entrySet().stream().filter(x -> x.getValue().equals(finalMaxValue)).mapToInt(x -> x.getKey())
				.min().getAsInt();
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = Result.migratoryBirds(arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
