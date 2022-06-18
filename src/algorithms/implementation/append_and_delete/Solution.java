package algorithms.implementation.append_and_delete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

	public static String appendAndDelete(String s, String t, int k) {
		int index = 0;

		for (int i = 0; i < Math.max(s.length(), t.length()) + 1; i++) {
			if (i == Math.min(s.length(), t.length()) || s.charAt(i) != t.charAt(i)) {
				index = i;
				break;
			}
		}

		int requiredChanges = s.length() - index + t.length() - index;

		if (requiredChanges == k)
			return "Yes";
		else if (requiredChanges % 2 == 0 && k % 2 == 0 && requiredChanges < k)
			return "Yes";
		else if (requiredChanges % 2 == 1 && k % 2 == 1 && requiredChanges < k)
			return "Yes";
		else if (index == 0 && requiredChanges < k)
			return "Yes";
		else if (k > s.length() + t.length())
			return "Yes";
		else
			return "No";
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		String t = bufferedReader.readLine();

		int k = Integer.parseInt(bufferedReader.readLine().trim());

		String result = Result.appendAndDelete(s, t, k);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
