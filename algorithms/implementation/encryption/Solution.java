package implementation.encryption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

	/*
	 * Complete the 'encryption' function below.
	 *
	 * The function is expected to return a STRING. The function accepts STRING
	 * s as parameter.
	 */

	public static String encryption(String s) {
		String unspacedS = s.replace(" ", "");
		double squaredRoot = Math.sqrt((double) unspacedS.length());

		int ceilValue = (int) Math.ceil(squaredRoot);

		String encodedValue = "";

		for (int i = 0; i < ceilValue; i++) {
			for (int j = 0; j < ceilValue; j++) {
				if (i + ceilValue * j < s.length()) {
					encodedValue += s.charAt(i + ceilValue * j);
				}
			}
			encodedValue += " ";
		}

		return encodedValue.trim();
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		String result = Result.encryption(s);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
