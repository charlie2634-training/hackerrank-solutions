package implementation.picking_numbers;

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
	 * Complete the 'pickingNumbers' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY a as parameter.
	 */

	public static int pickingNumbers(List<Integer> a) {
		Map<Integer, Integer> maxValues = new HashMap<>();

		for (int i = 0; i < a.size(); i++) {
			int currentKey = a.get(i);
			int previousKey = currentKey - 1;

			if (!maxValues.keySet().contains(currentKey))
				maxValues.put(currentKey, 0);
			if (!maxValues.keySet().contains(previousKey))
				maxValues.put(previousKey, 0);

			maxValues.put(currentKey, maxValues.get(currentKey) + 1);
			maxValues.put(previousKey, maxValues.get(previousKey) + 1);
		}

		return maxValues.values().stream().mapToInt(x -> x.intValue()).max().getAsInt();
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(toList());

		int result = Result.pickingNumbers(a);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
