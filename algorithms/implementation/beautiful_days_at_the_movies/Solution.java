package implementation.beautiful_days_at_the_movies;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class Result {

	/*
	 * Complete the 'beautifulDays' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * following parameters: 1. INTEGER i 2. INTEGER j 3. INTEGER k
	 */

	public static int beautifulDays(int i, int j, int k) {
		return (int) IntStream.rangeClosed(i, j)
				.map(x -> x - Integer.valueOf(new StringBuilder(String.valueOf(x)).reverse().toString()))
				.filter(x -> x % k == 0).count();
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int i = Integer.parseInt(firstMultipleInput[0]);

		int j = Integer.parseInt(firstMultipleInput[1]);

		int k = Integer.parseInt(firstMultipleInput[2]);

		int result = Result.beautifulDays(i, j, k);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
