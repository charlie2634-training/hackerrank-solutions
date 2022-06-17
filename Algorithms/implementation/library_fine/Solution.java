package implementation.library_fine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class Result {

	/*
	 * Complete the 'libraryFine' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * following parameters: 1. INTEGER d1 2. INTEGER m1 3. INTEGER y1 4.
	 * INTEGER d2 5. INTEGER m2 6. INTEGER y2
	 */

	public static int libraryFine(int d1, int m1, int y1, int d2, int m2, int y2) {
		Calendar returnDate = Calendar.getInstance();
		Calendar dueDate = Calendar.getInstance();

		returnDate.set(y1, m1 - 1, d1, 0, 0, 0);
		dueDate.set(y2, m2 - 1, d2, 0, 0, 0);
		returnDate.set(Calendar.MILLISECOND, 0);
		dueDate.set(Calendar.MILLISECOND, 0);

		if (returnDate.before(dueDate)) {
			return 0;
		} else if (returnDate.get(Calendar.MONTH) == dueDate.get(Calendar.MONTH)
				&& returnDate.get(Calendar.YEAR) == dueDate.get(Calendar.YEAR)) {
			return (int) getDifferenceDays(dueDate.getTime(), returnDate.getTime()) * 15;
		} else if (returnDate.get(Calendar.MONTH) != dueDate.get(Calendar.MONTH)
				&& returnDate.get(Calendar.YEAR) == dueDate.get(Calendar.YEAR)) {
			return (returnDate.get(Calendar.MONTH) - dueDate.get(Calendar.MONTH)) * 500;
		} else if (returnDate.get(Calendar.YEAR) != dueDate.get(Calendar.YEAR)) {
			return 10_000;
		}

		return 0;
	}

	public static long getDifferenceDays(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int d1 = Integer.parseInt(firstMultipleInput[0]);

		int m1 = Integer.parseInt(firstMultipleInput[1]);

		int y1 = Integer.parseInt(firstMultipleInput[2]);

		String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int d2 = Integer.parseInt(secondMultipleInput[0]);

		int m2 = Integer.parseInt(secondMultipleInput[1]);

		int y2 = Integer.parseInt(secondMultipleInput[2]);

		int result = Result.libraryFine(d1, m1, y1, d2, m2, y2);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
