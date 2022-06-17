package warmup.staircase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.IntStream;

class Result {

	/*
	 * Complete the 'staircase' function below.
	 *
	 * The function accepts INTEGER n as parameter.
	 */

	public static void staircase(int n) {
		IntStream.range(0, n).forEach(x -> {
			System.out.print(String.join("", Collections.nCopies(n - (x + 1), " ")));
			System.out.println(String.join("", Collections.nCopies(x + 1, "#")));
		});
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		Result.staircase(n);

		bufferedReader.close();
	}
}
