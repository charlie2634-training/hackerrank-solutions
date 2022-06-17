package implementation.cut_the_sticks;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'cutTheSticks' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */

	public static List<Integer> cutTheSticks(List<Integer> arr) {
		List<Integer> returnable = new ArrayList<>();
		returnable.add(arr.size());

		while (true) {
			int minValue = arr.stream().mapToInt(x -> x.intValue()).min().getAsInt();
			arr = arr.stream().map(x -> x - minValue).filter(x -> x != 0).collect(Collectors.toList());

			if (arr.size() == 0) {
				break;
			} else {
				returnable.add(arr.size());
			}
		}

		return returnable;
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

		List<Integer> result = Result.cutTheSticks(arr);

		bufferedWriter.write(result.stream().map(Object::toString).collect(joining("\n")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}
