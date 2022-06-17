package game_theory.chocolate_in_box;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'chocolateInBox' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */

	public static int chocolateInBox(List<Integer> values) {
		int removableValue = values.stream().reduce(0, (x, y) -> x ^ y);
		Long count = values.stream().filter(x -> x > 0 && x > (x ^ removableValue)).count();
		return count.intValue();
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = Result.chocolateInBox(arr);

		System.out.println(String.valueOf(result));
	}
}
