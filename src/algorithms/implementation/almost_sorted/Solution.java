package algorithms.implementation.almost_sorted;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Result {

	static class KeyValuePair {
		private int key;
		private int value;

		public KeyValuePair(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return this.key;
		}

		public int getValue() {
			return this.value;
		}
	}

	public static void almostSorted(List<Integer> arr) {
		List<Integer> sorted = arr.stream().sorted().collect(Collectors.toList());
		List<KeyValuePair> invalidValues = new ArrayList<>();
		boolean validForReverse = true;

		for (int i = 0; i < sorted.size(); i++) {
			if (!sorted.get(i).equals(arr.get(i))) {
				if (invalidValues.size() > 0) {
					int previousValue = invalidValues.get(invalidValues.size() - 1).value;
					int newValue = arr.get(i);

					if (previousValue < newValue) {
						validForReverse = false;
					}
				}

				invalidValues.add(new KeyValuePair(i + 1, arr.get(i)));
			}
		}

		if (invalidValues.size() == 2) {
			System.out.println("yes");
			System.out
					.println(String.format("swap %d %d", invalidValues.get(0).getKey(), invalidValues.get(1).getKey()));
		} else if (validForReverse) {
			System.out.println("yes");
			System.out.println(String.format("reverse %d %d", invalidValues.get(0).getKey(),
					invalidValues.get(invalidValues.size() - 1).getKey()));
		} else {
			System.out.println("no");
		}
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		Result.almostSorted(arr);

		bufferedReader.close();
	}
}
