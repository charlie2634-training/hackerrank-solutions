package algorithms.implementation.between_two_sets;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'getTotalX' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * following parameters: 1. INTEGER_ARRAY a 2. INTEGER_ARRAY b
	 */

	public static int getTotalX(List<Integer> a, List<Integer> b) {
		int counter = 0;
		int maxBValue = b.stream().max(Comparator.comparing(x -> x)).get();

		List<Integer> possibleFactors = new ArrayList<>();

		for (int i = 1; i <= maxBValue; i++) {
			if (maxBValue % i == 0)
				possibleFactors.add(i);
		}

		for (Integer possibleFactor : possibleFactors) {
			if (checkPossibleFactor(possibleFactor, a, b))
				counter++;
		}

		return counter;
	}

	public static boolean checkPossibleFactor(Integer possibleFactor, List<Integer> a, List<Integer> b) {
		for (Integer aValue : a) {
			if (possibleFactor % aValue != 0)
				return false;
		}
		for (Integer bValue : b) {
			if (bValue % possibleFactor != 0)
				return false;
		}
		return true;
	}
}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int m = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int total = Result.getTotalX(arr, brr);

		bufferedWriter.write(String.valueOf(total));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
