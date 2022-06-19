package java.bignumber.java_bigdecimal;

import java.math.BigDecimal;
import java.util.*;

class Solution {
	public static void main(String[] args) {
		// Input
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] s = new String[n + 2];
		for (int i = 0; i < n; i++) {
			s[i] = sc.next();
		}
		sc.close();

		s = Arrays.asList(s).stream().filter(x -> x != null)
				.sorted((String x, String y) -> new BigDecimal(y).compareTo(new BigDecimal(x)))
				.collect(java.util.stream.Collectors.toList()).toArray(new String[0]);

		// Output
		for (int i = 0; i < n; i++) {
			System.out.println(s[i]);
		}
	}
}