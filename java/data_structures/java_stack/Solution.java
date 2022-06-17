package data_structures.java_stack;

import java.util.*;
import java.util.regex.*;

class Solution {

	private static String regex = "(\\[\\]|\\{\\}|\\(\\))";
	private static Pattern pattern = Pattern.compile(regex);

	public static void main(String[] argh) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			String input = sc.next();
			System.out.println(isBalanced(input));
		}
		sc.close();
	}

	private static boolean isBalanced(String value) {
		while (!value.isEmpty()) {
			Matcher matcher = pattern.matcher(value);
			boolean replacedValue = false;

			while (matcher.find()) {
				replacedValue = true;
				value = value.replace(matcher.group(0), "");

				if (value.isEmpty())
					return true;
			}

			if (!replacedValue)
				return false;
		}

		return false;
	}
}
