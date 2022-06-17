package implementation.breaking_the_records;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'breakingRecords' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY. The function accepts
	 * INTEGER_ARRAY scores as parameter.
	 */

	public static List<Integer> breakingRecords(List<Integer> scores) {
		int currentMax = Integer.MIN_VALUE;
		int currentMin = Integer.MAX_VALUE;
		int numberOfMaxRecordBreaks = -1;
		int numberOfMinRecordBreaks = -1;

		for (Integer score : scores) {
			if (score > currentMax) {
				currentMax = score;
				numberOfMaxRecordBreaks++;
			}
			if (score < currentMin) {
				currentMin = score;
				numberOfMinRecordBreaks++;
			}
		}

		return Arrays.asList(numberOfMaxRecordBreaks, numberOfMinRecordBreaks);
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> scores = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		List<Integer> result = Result.breakingRecords(scores);

		bufferedWriter.write(result.stream().map(Object::toString).collect(joining(" ")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}
