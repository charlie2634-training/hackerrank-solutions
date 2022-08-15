package one_week_preparation_kit.day_c.caesar_cipher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

	/*
	 * Complete the 'caesarCipher' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * following parameters: 1. STRING s 2. INTEGER k
	 */

	public static String caesarCipher(String s, int k) {
		String result = "";

		for (char character : s.toCharArray()) {
			if (character - 97 >= 0 && character - 97 < 26) {
				result += (char) (((character - 97 + k) % 26) + 97);
			} else if (character - 65 >= 0 && character - 65 < 26) {
				result += (char) (((character - 65 + k) % 26) + 65);
			} else {
				result += character;
			}
		}

		return result;
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {

		System.out.println((int) 'a');
		System.out.println((int) 'z');
		System.out.println((int) 'A');
		System.out.println((int) 'Z');
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedWriter bufferedWriter = new BufferedWriter(new
		// FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		String s = bufferedReader.readLine();

		int k = Integer.parseInt(bufferedReader.readLine().trim());

		String result = Result.caesarCipher(s, k);

		System.out.println("result = " + result);

		// bufferedWriter.write(result);
		// bufferedWriter.newLine();

		bufferedReader.close();
		// bufferedWriter.close();
	}
}
