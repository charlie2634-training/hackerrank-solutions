package algorithms.implementation.viral_advertising;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

	/*
	 * Complete the 'viralAdvertising' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER n as parameter.
	 */

	public static int viralAdvertising(int n) {
		int shared = 5;
		int cumulative = 0;
		for (int i = 0; i < n; i++) {
			int liked = (shared / 2);
			shared = liked * 3;
			cumulative += liked;
		}
		return cumulative;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		int result = Result.viralAdvertising(n);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
