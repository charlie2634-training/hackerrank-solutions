package java.strings.java_strings_introduction;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String A = sc.next();
		String B = sc.next();
		/* Enter your code here. Print output to STDOUT. */

		System.out.println(A.length() + B.length());

		for (int i = 0; i < Math.min(A.length(), B.length()); i++) {
			Character charA = A.charAt(i);
			Character charB = B.charAt(i);

			int compareValue = charA.compareTo(charB);
			if (compareValue < 0) {
				System.out.println("No");
				break;
			} else if (compareValue > 0) {
				System.out.println("Yes");
				break;
			}
		}

		sc.close();
		
		System.out.printf("%s %s\n", capitalizeFirstLetter(A), capitalizeFirstLetter(B));

	}

	private static String capitalizeFirstLetter(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}
}
