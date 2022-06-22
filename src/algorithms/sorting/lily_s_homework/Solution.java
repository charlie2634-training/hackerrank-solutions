package algorithms.sorting.lily_s_homework;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'lilysHomework' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */

	public static int lilysHomework(List<Integer> arr) {
		Map<Integer, Integer[]> indexes = new TreeMap<>();
		Map<Integer, Integer> originalLocations = new HashMap<>();
		Map<Integer, Integer> originalInversedLocations = new HashMap<>();

		for (int i = 0; i < arr.size(); i++) {
			indexes.put(arr.get(i), new Integer[] { i, arr.size() - i - 1 });
			originalLocations.put(i, arr.get(i));
			originalInversedLocations.put(arr.size() - i - 1, arr.get(i));
		}

		int counterA = 0, counterB = 0;

		Integer index = 0;
		for (Entry<Integer, Integer[]> entry : indexes.entrySet()) {
			Integer[] indexesEntry = entry.getValue();

			if (!indexesEntry[0].equals(index)) {
				Integer valueAtLocation = originalLocations.get(index);

				originalLocations.put(index, entry.getKey());
				originalLocations.put(indexesEntry[0], valueAtLocation);

				indexes.get(valueAtLocation)[0] = indexesEntry[0];
				indexesEntry[0] = index;
				counterA++;
			}
			if (!indexesEntry[1].equals(index)) {
				Integer valueAtLocation = originalInversedLocations.get(index);

				originalInversedLocations.put(index, entry.getKey());
				originalInversedLocations.put(indexesEntry[1], valueAtLocation);

				indexes.get(valueAtLocation)[1] = indexesEntry[1];
				indexesEntry[1] = index;
				counterB++;
			}

			index++;
		}

		return Math.min(counterA, counterB);
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

		int result = Result.lilysHomework(arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
