package warmup.time_conversion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

class Result {

	/*
	 * Complete the 'timeConversion' function below.
	 *
	 * The function is expected to return a STRING. The function accepts STRING
	 * s as parameter.
	 */

	public static String timeConversion(String s) {
		try {
			SimpleDateFormat amPMdateFormat = new SimpleDateFormat("hh:mm:ssa");
			Date date = amPMdateFormat.parse(s);
			SimpleDateFormat militaryDateFormat = new SimpleDateFormat("HH:mm:ss");
			return militaryDateFormat.format(date);
		} catch (Exception ex) {
			return null;
		}

	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		String result = Result.timeConversion(s);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
