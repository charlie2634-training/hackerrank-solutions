package strings.java_anagrams;

import java.util.Scanner;

public class Solution {

	static boolean isAnagram(String a, String b) {
		char[] chars = a.toLowerCase().toCharArray();
		java.util.Arrays.sort(chars);
		String sortedA = new String(chars);

		chars = b.toLowerCase().toCharArray();
		java.util.Arrays.sort(chars);
		String sortedB = new String(chars);

		return sortedA.equals(sortedB);
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String a = scan.next();
		String b = scan.next();
		scan.close();
		boolean ret = isAnagram(a, b);
		System.out.println((ret) ? "Anagrams" : "Not Anagrams");
	}
}