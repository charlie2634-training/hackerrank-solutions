package algorithms.strings.sherlock_and_the_valid_string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Result {

	public static String isValid(String s) {
		Map<Character, Integer> values = new HashMap<>();

		// Complexity of N.
		for (int i = 0; i < s.length(); i++) {
			values.putIfAbsent(s.charAt(i), 0);
			values.put(s.charAt(i), values.get(s.charAt(i)) + 1);
		}

		Map<Integer, Integer> count = new HashMap<>();

		// Complexity of 1 (best case) to 27 (worst case, all letters in the
		// string).
		for (Integer value : values.values()) {
			count.putIfAbsent(value, 0);
			count.put(value, count.get(value) + 1);
		}

		if (count.size() == 1) {
			return "YES";
		} else if (count.size() == 2) {
			List<Entry<Integer, Integer>> counters = count.entrySet().stream().collect(Collectors.toList());

			Entry<Integer, Integer> firstEntry = counters.get(0);
			Entry<Integer, Integer> secondEntry = counters.get(1);

			if (firstEntry.getKey().equals(1) && firstEntry.getValue().equals(1)) {
				return "YES";
			}

			Integer differenceBetweenValues = Math.abs(firstEntry.getKey() - secondEntry.getKey());
			boolean anyValueIsOnlyOnce = (firstEntry.getValue().equals(1) || secondEntry.getValue().equals(1));

			if (differenceBetweenValues.equals(1) && anyValueIsOnlyOnce) {
				return "YES";
			}
		}

		return "NO";
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		String result = Result.isValid(s);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
