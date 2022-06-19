package algorithms.implementation.chocolate_feast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class Result {

	/*
	 * Complete the 'chocolateFeast' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * following parameters: 1. INTEGER n 2. INTEGER c 3. INTEGER m
	 */

	public static int chocolateFeast(int n, int c, int m) {
		int accumulatedChocolate = n / c;
		int wrappers = accumulatedChocolate;

		while (wrappers >= m) {
			int newChocolatesToBuy = wrappers / m;
			wrappers = wrappers % m + newChocolatesToBuy;
			accumulatedChocolate += newChocolatesToBuy;
		}

		return accumulatedChocolate;
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

				int c = Integer.parseInt(firstMultipleInput[1]);

				int m = Integer.parseInt(firstMultipleInput[2]);

				int result = Result.chocolateFeast(n, c, m);

				bufferedWriter.write(String.valueOf(result));
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
