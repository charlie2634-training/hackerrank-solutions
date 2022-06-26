package algorithms.strings.highest_value_palindrome;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Result {

	/*
	 * Complete the 'highestValuePalindrome' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * following parameters: 1. STRING s 2. INTEGER n 3. INTEGER k
	 */
	public static String highestValuePalindrome(String s, int n, int k) {
		int startingIndex = n / 2;
		int additionalIndex = n % 2 == 0 ? 1 : 0;
		char[] charArray = s.toCharArray();
		Map<Integer, Integer> updatedIndexes = new HashMap<>();

		// Complexity of O(N /2).
		for (int i = 1; i <= charArray.length / 2; i++) {
			char a = charArray[startingIndex + i - additionalIndex];
			char b = charArray[startingIndex - i];

			if (a != b) {
				if (k > 0) {
					char maxValue = (char) Math.max(a, b);
					k--;
					charArray[startingIndex + i - additionalIndex] = maxValue;
					charArray[startingIndex - i] = maxValue;
					updatedIndexes.put(startingIndex - i, Integer.valueOf(maxValue));
				} else
					return "-1";
			}
		}

		// Here charArray should be palindrome. Now we have to figure out how to
		// spend our remaining k.

		// Complexity of: best case O(1), worst case O(N / 2 + 1).
		for (int i = 0; i <= charArray.length / 2 && k > 0; i++) {
			if (charArray[i] != '9') {
				if (updatedIndexes.keySet().contains(i) && k >= 1) {
					charArray[i] = '9';
					charArray[charArray.length - i - 1] = '9';
					k--;
				} else if (k >= 2) {
					charArray[i] = '9';
					charArray[charArray.length - i - 1] = '9';
					k -= 2;
				}
			}
		}

		if (k == 1 && n % 2 == 1 && charArray[charArray.length / 2] != '9') {
			charArray[charArray.length / 2] = '9';
		}

		return new String(charArray);

	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		String s = bufferedReader.readLine();

		String result = Result.highestValuePalindrome(s, n, k);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
