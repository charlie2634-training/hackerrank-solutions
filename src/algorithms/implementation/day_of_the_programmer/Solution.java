package algorithms.implementation.day_of_the_programmer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

	/*
	 * Complete the 'dayOfProgrammer' function below.
	 *
	 * The function is expected to return a STRING. The function accepts INTEGER
	 * year as parameter.
	 */

	public static String dayOfProgrammer(int year) {
		String returnable = "";

		if (year > 1918) {
			if (year % 400 == 0) {
				returnable = "12.09." + String.valueOf(year);
			} else if (year % 4 == 0 && year % 100 != 0) {
				returnable = "12.09." + String.valueOf(year);
			} else {
				returnable = "13.09." + String.valueOf(year);
			}
		} else if (year == 1918) {
			returnable = "26.09.1918";
		} else if (year < 1918) {
			if (year % 4 == 0) {
				returnable = "12.09." + String.valueOf(year);
			} else {
				returnable = "13.09." + String.valueOf(year);
			}
		}

		return returnable;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int year = Integer.parseInt(bufferedReader.readLine().trim());

		String result = Result.dayOfProgrammer(year);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
