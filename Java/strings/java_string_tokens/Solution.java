package strings.java_string_tokens;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String value = scan.nextLine();

		List<String> matches = Arrays.asList(value.split("[^(A-Za-z)+]")).stream().filter(x -> !x.isEmpty())
				.collect(java.util.stream.Collectors.toList());

		System.out.println(matches.size());
		matches.stream().forEach(x -> System.out.println(x));

		scan.close();
	}
}
