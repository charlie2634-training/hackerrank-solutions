package implementation.palindromic_border;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Result {

	private static final List<Character> availableChars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');

	private static String word;
	private static List<Character> matchedCharacters;
	private static List<String> visitedDeepest;

	public static int palindromicBorder(String s) {
		word = s;
		matchedCharacters = availableChars.stream().filter(x -> word.indexOf(x) >= 0).collect(Collectors.toList());
		visitedDeepest = new ArrayList<>();

		if (matchedCharacters.size() == 1) {
			BigInteger accumulated = BigInteger.valueOf(0);

			for (int i = 1; i < s.length(); i++) {
				BigInteger AddedValue = BigInteger.valueOf(s.length() - i).multiply(BigInteger.valueOf(i));

				accumulated = accumulated.add(AddedValue);
			}

			return accumulated.mod(new BigInteger("1000000007")).intValue();
		}

		return searchForPattern("", 0).mod(new BigInteger("1000000007")).intValue();
	}

	public static BigInteger searchForPattern(String pattern, long parentLength) {
		BigInteger accumulated = BigInteger.valueOf(0);

		if (parentLength < 100) {
			for (char availableChar : matchedCharacters) {
				List<String> derivedPatterns = new ArrayList<>();

				if (pattern.isEmpty())
					derivedPatterns.add(String.valueOf(availableChar));
				derivedPatterns.add(availableChar + pattern + availableChar);

				for (String nextPattern : derivedPatterns) {
					int occurrences = 0;
					int index = word.indexOf(nextPattern, 0);

					BigInteger accumulated2 = BigInteger.valueOf(0);

					while (index >= 0) {
						occurrences++;
						accumulated = accumulated.add(BigInteger.valueOf(occurrences - 1));
						accumulated2 = accumulated2.add(BigInteger.valueOf(occurrences - 1));
						index = word.indexOf(nextPattern, index + 1);
					}

					if (occurrences > 1) {
						// System.out.printf("normal: occurrences: %d \t\t
						// acumulated: %d \t pattern: %s", occurrences,
						// accumulated2.intValue(), nextPattern);
						accumulated = accumulated.add(searchForPattern(nextPattern, nextPattern.length()));
					}
				}
			}
		} else {
			int index = word.indexOf(pattern, 0);

			List<String> derivedDeepest = new ArrayList<>();

			while (index >= 0) {
				String deepestPalyndrome = DeepPalyndromeIdentifier.findDeepestPalyndrome(index, pattern);
				if (!derivedDeepest.contains(deepestPalyndrome) && deepestPalyndrome.length() > pattern.length())
					derivedDeepest.add(deepestPalyndrome);
				index = word.indexOf(pattern, index + 1);
			}

			Collections.sort(derivedDeepest,
					(a, b) -> Integer.valueOf(a.length()).compareTo(Integer.valueOf(b.length())));

			for (String deepest : derivedDeepest) {
				String hashCode = String.valueOf(deepest.hashCode());

				if (!visitedDeepest.contains(hashCode)) {
					visitedDeepest.add(hashCode);
					int occurrences = 0;
					int deepestPatternIndex = word.indexOf(deepest, 0);
					long differenceAgainstFatter = (deepest.length() - parentLength) / 2;

					BigInteger accumulated2 = BigInteger.valueOf(0);
					while (deepestPatternIndex >= 0) {
						occurrences++;
						accumulated = accumulated.add(BigInteger.valueOf(occurrences - 1)
								.multiply(BigInteger.valueOf(differenceAgainstFatter)));
						accumulated2 = accumulated2.add(BigInteger.valueOf(occurrences - 1)
								.multiply(BigInteger.valueOf(differenceAgainstFatter)));
						deepestPatternIndex = word.indexOf(deepest, deepestPatternIndex + 1);
					}

					// System.out.printf("deep: occurrences: %d \t\t acumulated:
					// %d \t pattern: %s\n", occurrences,
					// accumulated2.intValue(), deepest);
					accumulated = accumulated.add(searchForPattern(deepest, deepest.length()));
				}
			}
		}

		return accumulated;
	}

	static class DeepPalyndromeIdentifier {

		private static int interval = 100;

		private static String findPalyndrome(int leftIndex, int rightIndex, String leftSubstring,
				String rightSubstring) {
			int newPossibleLeftIndex = leftIndex;
			int newPossiblerightIndex = rightIndex;

			for (int j = 1; j < leftSubstring.length(); j++) {
				if (leftSubstring.substring(0, j).equals(rightSubstring.substring(0, j))) {
					newPossibleLeftIndex--;
					newPossiblerightIndex++;
				} else {
					return word.substring(newPossibleLeftIndex, newPossiblerightIndex);
				}
			}

			return null;
		}

		private static String findDeepestPalyndrome(int index, String pattern) {
			int leftIndex = index;
			int rightIndex = leftIndex + pattern.length();

			for (int i = interval; i < word.length(); i = i + interval) {
				int newLeftIndex = Math.max(leftIndex - i, 0);
				int newrightIndex = Math.min(rightIndex + i, word.length());

				if (leftIndex - newLeftIndex != newrightIndex - rightIndex) {
					int differenceToPreviousLeftIndex = Math.abs(leftIndex - newLeftIndex);
					int differenceToPreviousRightIndex = Math.abs(rightIndex - newrightIndex);

					newLeftIndex = leftIndex - Math.min(differenceToPreviousLeftIndex, differenceToPreviousRightIndex);
					newrightIndex = rightIndex
							+ Math.min(differenceToPreviousLeftIndex, differenceToPreviousRightIndex);
				}

				String leftSubstring = new StringBuilder(word.substring(newLeftIndex, leftIndex)).reverse().toString();
				String rightSubstring = word.substring(rightIndex, newrightIndex);

				if (leftSubstring.equals(rightSubstring)) {
					leftIndex = newLeftIndex;
					rightIndex = newrightIndex;
				} else {
					return findPalyndrome(leftIndex, rightIndex, leftSubstring, rightSubstring);
				}
				if (leftIndex == 0 || rightIndex == word.length()) {
					break;
				}
			}

			return word.substring(leftIndex, rightIndex);
		}
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		int result = Result.palindromicBorder(s);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
