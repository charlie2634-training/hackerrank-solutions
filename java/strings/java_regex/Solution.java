package strings.java_regex;

import java.util.Scanner;

class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String IP = in.next();
			System.out.println(IP.matches(new MyRegex().pattern));
		}
		in.close();
	}
}

// Write your code here

class MyRegex {
	private String validIpNumberRegex = "([0]?[0]?[0-9]|[0]?[1-9][0-9]|[1][0-9][0-9]|[2][0-4][0-9]|[2][5][0-5])";
	private String ipFormat = "^%s\\.%s\\.%s\\.%s";

	public String pattern = String.format(ipFormat, validIpNumberRegex, validIpNumberRegex, validIpNumberRegex,
			validIpNumberRegex);
}
