package implementation.number_line_jumps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

	/*
	 * Complete the 'kangaroo' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * following parameters: 1. INTEGER x1 2. INTEGER v1 3. INTEGER x2 4.
	 * INTEGER v2
	 */

	public static String kangaroo(int startLocationA, int rateA, int startLocationB, int rateB) {
		double x = ((double) (startLocationB - startLocationA)) / ((double) (rateA - rateB));

		if ((x % 1.0) == 0 && x > 0)
			return "YES";
		return "NO";
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int x1 = Integer.parseInt(firstMultipleInput[0]);

		int v1 = Integer.parseInt(firstMultipleInput[1]);

		int x2 = Integer.parseInt(firstMultipleInput[2]);

		int v2 = Integer.parseInt(firstMultipleInput[3]);

		String result = Result.kangaroo(x1, v1, x2, v2);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
