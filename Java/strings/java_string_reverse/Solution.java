package strings.java_string_reverse;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String word = sc.next();
		/* Enter your code here. Print output to STDOUT. */
		sc.close();
		System.out.println(isPalindrome(word) ? "Yes" : "No");
	}

	private static boolean isPalindrome(String word) {
		for (int i = 0; i < (word.length() / 2); i++) {
			if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
				return false;
			}
		}

		return true;
	}
}
