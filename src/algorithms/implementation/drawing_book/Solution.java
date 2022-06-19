package algorithms.implementation.drawing_book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

	/*
	 * Complete the 'pageCount' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * following parameters: 1. INTEGER n 2. INTEGER p
	 */

	public static int pageCount(int n, int p) {
		int pagesFromStart = (p - (p % 2)) / 2;
		int pagesFromEnd = ((n - (n % 2)) - (p - (p % 2))) / 2;
		return Math.min(pagesFromStart, pagesFromEnd);
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		int p = Integer.parseInt(bufferedReader.readLine().trim());

		int result = Result.pageCount(n, p);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
