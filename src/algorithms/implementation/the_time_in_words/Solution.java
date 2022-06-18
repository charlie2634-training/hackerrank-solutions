package algorithms.implementation.the_time_in_words;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Result {

	private static final String O_CLOCK_TEMPLATE = "%s o' clock";
	private static final String PAST_TEMPLATE = "%s %spast %s";
	private static final String TO_TEMPLATE = "%s %sto %s";

	private static final String[] NUMBERS = new String[] { "one", "two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen",
			"eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five",
			"twenty six", "twenty seven", "twenty eight", "twenty nine", "half" };

	public static String timeInWords(int h, int m) {
		String minuteString = "";
		if (m == 1)
			minuteString = "minute ";
		if (m > 1)
			minuteString = "minutes ";
		if (Arrays.asList(15, 30, 45).contains(m))
			minuteString = "";

		if (m == 0) {
			return String.format(O_CLOCK_TEMPLATE, NUMBERS[h - 1]);
		} else if (m <= 30) {
			return String.format(PAST_TEMPLATE, NUMBERS[m - 1], minuteString, NUMBERS[h - 1]);
		} else {
			return String.format(TO_TEMPLATE, NUMBERS[60 - (m + 1)], minuteString, NUMBERS[h]);
		}
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int h = Integer.parseInt(bufferedReader.readLine().trim());

		int m = Integer.parseInt(bufferedReader.readLine().trim());

		String result = Result.timeInWords(h, m);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
