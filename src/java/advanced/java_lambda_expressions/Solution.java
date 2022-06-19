package java.advanced.java_lambda_expressions;

import java.io.*;
import java.util.*;

interface PerformOperation {
	boolean check(int a);
}

class MyMath {
	public static boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}

	public PerformOperation isOdd() {
		return x -> x % 2 == 1;
	}

	public PerformOperation isPrime() {
		return x -> {
			boolean isPrime = true;
			for (int i = 2; i < (x / 2); i++) {
				if (x % i == 0) {
					isPrime = false;
					break;
				}
			}
			return isPrime;
		};
	}

	public PerformOperation isPalindrome() {
		return x -> {
			String convertedValue = String.valueOf(x);
			return convertedValue.equals(new StringBuilder(convertedValue).reverse().toString());
		};
	}
}

public class Solution {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		MyMath ob = new MyMath();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		PerformOperation op;
		boolean ret = false;
		String ans = null;
		while (T-- > 0) {
			String s = br.readLine().trim();
			StringTokenizer st = new StringTokenizer(s);
			int ch = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (ch == 1) {
				op = ob.isOdd();
				ret = ob.checker(op, num);
				ans = (ret) ? "ODD" : "EVEN";
			} else if (ch == 2) {
				op = ob.isPrime();
				ret = ob.checker(op, num);
				ans = (ret) ? "PRIME" : "COMPOSITE";
			} else if (ch == 3) {
				op = ob.isPalindrome();
				ret = ob.checker(op, num);
				ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

			}
			System.out.println(ans);
		}
	}
}
