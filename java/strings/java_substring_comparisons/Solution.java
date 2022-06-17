package strings.java_substring_comparisons;

import java.util.Scanner;

public class Solution {

	public static String getSmallestAndLargest(String value, int k) {
		String smallest = "";
		String largest = "";

		for (int i = 0; i <= value.length() - k; i++) {
			String currentSubstring = value.substring(i, i + k);

			if (i == 0) {
				smallest = currentSubstring;
				largest = currentSubstring;
			}

			if (currentSubstring.compareTo(smallest) < 0)
				smallest = currentSubstring;
			if (currentSubstring.compareTo(largest) > 0)
				largest = currentSubstring;
		}

		return smallest + "\n" + largest;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		int k = scan.nextInt();
		scan.close();

		System.out.println(getSmallestAndLargest(s, k));
	}
}