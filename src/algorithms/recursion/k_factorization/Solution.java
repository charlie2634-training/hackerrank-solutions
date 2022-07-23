package algorithms.recursion.k_factorization;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

	public static List<Integer> kFactorization(int n, List<Integer> A) {
		List<Integer> factors = new ArrayList<>();

		for (int i = 0; i < A.size(); i++) {
			int currentValue = A.get(A.size() - i - 1);

			while (n % currentValue == 0) {
				factors.add(currentValue);
				n = n / currentValue;
			}
		}

		if (n != 1)
			return Arrays.asList(Integer.valueOf(-1));
		List<Integer> returnable = new ArrayList<>();

		returnable.add(1);

		for (int i = 0; i < factors.size(); i++) {
			int currentFactor = factors.get(factors.size() - i - 1);
			int newFactor = returnable.get(returnable.size() - 1) * currentFactor;

			returnable.add(newFactor);
		}

		return returnable;

	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.sorted().collect(toList());

		List<Integer> result = Result.kFactorization(n, A);

		bufferedWriter.write(result.stream().map(Object::toString).collect(joining(" ")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}
