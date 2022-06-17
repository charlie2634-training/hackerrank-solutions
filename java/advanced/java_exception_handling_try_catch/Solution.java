package advanced.java_exception_handling_try_catch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String firstValue = scanner.nextLine();
		String secondValue = scanner.nextLine();

		try {
			int firstCastedValue = Integer.valueOf(firstValue);
			int secondCastedValue = Integer.valueOf(secondValue);
			System.out.println(firstCastedValue / secondCastedValue);
		} catch (InputMismatchException | NumberFormatException ex) {
			System.out.println("java.util.InputMismatchException");
		} catch (ArithmeticException ex) {
			System.out.println("java.lang.ArithmeticException: / by zero");
		}

		scanner.close();
	}
}
