package implementation.repeated_string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Result {

	/*
	 * Complete the 'repeatedString' function below.
	 *
	 * The function is expected to return a LONG_INTEGER. The function accepts
	 * following parameters: 1. STRING s 2. LONG_INTEGER n
	 */
	public static long repeatedString(String s, long n) {
		List<Integer> positionOfChars = new ArrayList<>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'a')
				positionOfChars.add(i);
		}

		long numberOfRepeatedStrings = n / s.length();
		long remainingCharacters = n % s.length();

		long number1 = positionOfChars.size() * numberOfRepeatedStrings;
		long number2 = positionOfChars.stream().filter(x -> x < remainingCharacters).count();

		return number1 + number2;

	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		long n = Long.parseLong(bufferedReader.readLine().trim());

		long result = Result.repeatedString(s, n);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
