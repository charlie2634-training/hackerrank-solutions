package algorithms.sorting.fraudulent_activity_notifications;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Stream;

class Result {

	public static int activityNotifications(List<Integer> expenditure, int d) {
		int count = 0;
		boolean isEven = (d % 2 == 0);

		Map<Double, Integer> values = new TreeMap<>();
		Queue<Double> queue = new LinkedList<>();

		for (int i = 0; i < expenditure.size(); i++) {
			Double value = (double) expenditure.get(i);

			if (i >= d) {
				Double median = null;
				Double medianA = null;
				Double medianB = null;
				int indexCounter = 0;

				for (Entry<Double, Integer> entryValue : values.entrySet()) {
					int nextIndexCounter = indexCounter + entryValue.getValue();

					if (isEven) {
						if (indexCounter < (d / 2) && (d / 2) <= nextIndexCounter && medianA == null) {
							medianA = entryValue.getKey();
						}
						if (indexCounter < (d / 2) + 1 && (d / 2) + 1 <= nextIndexCounter) {
							medianB = entryValue.getKey();
							median = (medianA + medianB) / 2.0;
							break;
						}
					} else {
						if (indexCounter < (d / 2) + 1 && (d / 2) + 1 <= nextIndexCounter) {
							median = entryValue.getKey();
							break;
						}
					}

					indexCounter = nextIndexCounter;
				}
				if (value >= (median * 2)) {
					count++;
				}

				Double removedValue = queue.poll();
				values.put(removedValue, values.get(removedValue) - 1);
			}

			values.putIfAbsent(value, 0);
			values.put(value, values.get(value) + 1);
			queue.add(value);
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

		int d = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = Result.activityNotifications(expenditure, d);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
