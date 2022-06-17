package introduction.java_currency_formatter;

import java.util.*;
import java.text.*;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double payment = scanner.nextDouble();
		scanner.close();

		// Write your code here.

		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.FRENCH);
		formatSymbols.setDecimalSeparator(',');
		formatSymbols.setGroupingSeparator(' ');

		System.out.println("US: " + String.format("%c%s", '\u0024', new DecimalFormat("#,###.00").format(payment)));
		System.out.println("India: " + String.format("Rs.%s", new DecimalFormat("#,###.00").format(payment)));
		System.out.println("China: " + String.format("%c%s", '\uFFE5', new DecimalFormat("#,###.00").format(payment)));
		System.out.println("France: "
				+ String.format("%s %c", new DecimalFormat("#,###.00", formatSymbols).format(payment), '\u20AC'));
	}
}
