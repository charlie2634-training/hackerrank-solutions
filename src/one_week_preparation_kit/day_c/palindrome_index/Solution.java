package one_week_preparation_kit.day_c.palindrome_index;

class Result {
	public static int palindromeIndex(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(s.length() - (i + 1))) {
				String newString = s.substring(i, s.length() - (i + 1));
				if (isPalindrome(newString)) {
					return s.length() - (i + 1);
				} else {
					return i;
				}
			}
		}
		return -1;
	}

	public static boolean isPalindrome(String a) {
		for (int i = 0; i < a.length() / 2; i++) {
			if (a.charAt(i) != a.charAt(a.length() - (i + 1))) {
				return false;
			}
		}
		return true;
	}
}

public class Solution {
	
}