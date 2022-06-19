package algorithms.implementation.angry_professor;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'angryProfessor' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * following parameters: 1. INTEGER k 2. INTEGER_ARRAY a
	 */

	public static String angryProfessor(int k, List<Integer> a) {
		boolean classIsCanceled = a.stream().filter(x -> x <= 0).count() < k;

		return classIsCanceled ? "YES" : "NO";
	}

}

public class Solution {
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				int n = Integer.parseInt(firstMultipleInput[0]);

				int k = Integer.parseInt(firstMultipleInput[1]);

				List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList());

				String result = Result.angryProfessor(k, a);

				bufferedWriter.write(result);
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
