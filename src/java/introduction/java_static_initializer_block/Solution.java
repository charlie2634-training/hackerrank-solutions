package java.introduction.java_static_initializer_block;

import java.util.Scanner;

@SuppressWarnings("resource")
public class Solution {

	static int B, H;
	static boolean flag = true;

	// This is executed automatically when the static class is referenced using
	// main method.
	static {
		try {
			Scanner scanner = new Scanner(System.in);
			B = scanner.nextInt();
			H = scanner.nextInt();

			if (B <= 0 || H <= 0) {
				flag = false;
				throw new Exception("java.lang.Exception: Breadth and height must be positive");
			}
			scanner.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] args) {
		if (flag) {
			int area = B * H;
			System.out.print(area);
		}

	}// end of main

}// end of class
