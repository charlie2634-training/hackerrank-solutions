package algorithms.sorting.the_full_counting_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		Map<Integer, StringBuilder> map = new HashMap<>();

		for (int i = 0; i < n; i++) {
			String[] values = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
			Integer key = Integer.valueOf(values[0]);
			String value = (i < n / 2) ? "-" : values[1];

			map.putIfAbsent(key, new StringBuilder());
			if (map.get(key).length() == 0) {
				map.get(key).append(value);
			} else {
				map.get(key).append(" " + value);
			}
		}

		StringBuilder finalSB = new StringBuilder();

		boolean firstElement = true;
		for (Integer key : map.keySet()) {
			if (firstElement) {
				finalSB.append(map.get(key).toString());
				firstElement = false;
			} else {
				finalSB.append(" ");
				finalSB.append(map.get(key).toString());
			}
		}

		System.out.println(finalSB.toString());

		bufferedReader.close();
	}
}
