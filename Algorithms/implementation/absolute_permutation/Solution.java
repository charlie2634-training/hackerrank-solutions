package implementation.absolute_permutation;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Result {

	/*
	 * Complete the 'absolutePermutation' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY. The function accepts
	 * following parameters: 1. INTEGER n 2. INTEGER k
	 */

	public static List<Integer> absolutePermutation(int n, int k) {
		List<Integer> result = new ArrayList<>();

		if (k == 0) {
			for (int i = 1; i <= n; i++) {
				result.add(i);
			}
		} else if (n % 2 == 1) {
			result.add(-1);
		} else {
			if (n % (k * 2) != 0) {
				result.add(-1);
			} else {
				int numberOfGroups = n / (k * 2);

				for (int i = 0; i < numberOfGroups; i++) {
					for (int j = 0; j < (k * 2); j++) {
						int value = ((j + k) % (2 * k)) + (i * 2 * k) + 1;
						result.add(value);
					}
				}
			}
		}

		return result;
	}
}

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				int n = Integer.parseInt(firstMultipleInput[0]);

				int k = Integer.parseInt(firstMultipleInput[1]);

				List<Integer> result = Result.absolutePermutation(n, k);

				bufferedWriter.write(result.stream().map(Object::toString).collect(joining(" ")) + "\n");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
