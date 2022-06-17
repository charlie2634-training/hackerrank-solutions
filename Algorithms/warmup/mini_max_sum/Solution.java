package warmup.mini_max_sum;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'miniMaxSum' function below.
	 *
	 * The function accepts INTEGER_ARRAY arr as parameter.
	 */

	public static void miniMaxSum(List<Integer> arr) {
		Collections.sort(arr);
		System.out.printf("%d %d\n", arr.subList(0, 4).stream().mapToLong(x -> x.intValue()).sum(),
				arr.subList(arr.size() - 4, arr.size()).stream().mapToLong(x -> x.intValue()).sum());
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		Result.miniMaxSum(arr);

		bufferedReader.close();
	}
}
