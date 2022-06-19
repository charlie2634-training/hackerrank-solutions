package algorithms.implementation.equalize_the_array;

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
	 * Complete the 'equalizeArray' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */

	public static int equalizeArray(List<Integer> arr) {
		Map<Integer, Integer> map = new HashMap<>();
		int max = 0;

		for (Integer value : arr) {
			map.putIfAbsent(value, 0);
			map.put(value, map.get(value) + 1);

			if (map.get(value) > max)
				max = map.get(value);
		}

		return arr.size() - max;
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = Result.equalizeArray(arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
