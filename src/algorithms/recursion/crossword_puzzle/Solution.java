package algorithms.recursion.crossword_puzzle;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Result {

	/*
	 * Complete the 'crosswordPuzzle' function below.
	 *
	 * The function is expected to return a STRING_ARRAY. The function accepts
	 * following parameters: 1. STRING_ARRAY crossword 2. STRING words
	 */

	public static List<String> crosswordPuzzle(char[][] crossword, String serializedWords) {
		List<String> words = Arrays.asList(serializedWords.split(";"));
		return toStringList(tryToInsert(0, crossword, words));
	}

	public static char[][] tryToInsert(Integer currentI, char[][] crosswordPuzzle, List<String> availableWords) {

		// print(crosswordPuzzle);
		if (availableWords.size() == 0)
			return crosswordPuzzle;

		for (int i = currentI; i < crosswordPuzzle.length; i++) {
			for (int j = 0; j < crosswordPuzzle[i].length; j++) {
				if (crosswordPuzzle[i][j] != '+') {
					boolean isTheBeginningOfAVerticalWord = (i == 0 || crosswordPuzzle[i - 1][j] == '+');
					boolean isTheBeginningOfAnHorizontalWord = (j == 0 || crosswordPuzzle[i][j - 1] == '+');

					if (isTheBeginningOfAnHorizontalWord) {
						String replaceableHorizontal = "";

						for (int j2 = j; j2 < crosswordPuzzle[i].length; j2++) {
							if (crosswordPuzzle[i][j2] == '+') {
								break;
							}
							replaceableHorizontal += crosswordPuzzle[i][j2];
						}

						if (replaceableHorizontal.length() > 1 && replaceableHorizontal.contains("-")) {
							final int targetReplaceable = replaceableHorizontal.length();
							List<String> possibleReplacements = availableWords.stream()
									.filter(x -> x.length() == targetReplaceable).collect(Collectors.toList());

							for (int k = 0; k < replaceableHorizontal.length(); k++) {
								if (replaceableHorizontal.charAt(k) != '-') {
									final int indexFilter = k;
									final char character = replaceableHorizontal.charAt(k);
									possibleReplacements = possibleReplacements.stream()
											.filter(x -> x.charAt(indexFilter) == character)
											.collect(Collectors.toList());
								}
							}

							if (possibleReplacements.isEmpty())
								return null;

							char[][] result = null;

							for (String possibbleReplacement : possibleReplacements) {
								char[][] clone = clone(crosswordPuzzle);
								for (int k = 0; k < possibbleReplacement.length(); k++) {
									clone[i][k + j] = possibbleReplacement.charAt(k);
								}
								result = tryToInsert(i, clone, availableWords.stream()
										.filter(x -> !x.equals(possibbleReplacement)).collect(Collectors.toList()));
								if (result != null)
									return result;
							}

							if (result == null)
								return null;
						}
					}
					if (isTheBeginningOfAVerticalWord) {
						String replaceableVertical = "";

						for (int i2 = i; i2 < crosswordPuzzle.length; i2++) {
							if (crosswordPuzzle[i2][j] == '+') {
								break;
							}
							replaceableVertical += crosswordPuzzle[i2][j];
						}

						if (replaceableVertical.length() > 1 && replaceableVertical.contains("-")) {
							final int targetReplaceable = replaceableVertical.length();
							List<String> possibleReplacements = availableWords.stream()
									.filter(x -> x.length() == targetReplaceable).collect(Collectors.toList());

							for (int k = 0; k < replaceableVertical.length(); k++) {
								if (replaceableVertical.charAt(k) != '-') {
									final int indexFilter = k;
									final char character = replaceableVertical.charAt(k);
									possibleReplacements = possibleReplacements.stream()
											.filter(x -> x.charAt(indexFilter) == character)
											.collect(Collectors.toList());
								}
							}

							if (possibleReplacements.isEmpty())
								return null;

							char[][] result = null;

							for (String possibbleReplacement : possibleReplacements) {
								char[][] clone = clone(crosswordPuzzle);
								for (int k = 0; k < possibbleReplacement.length(); k++) {
									clone[k + i][j] = possibbleReplacement.charAt(k);
								}
								result = tryToInsert(i, clone, availableWords.stream()
										.filter(x -> !x.equals(possibbleReplacement)).collect(Collectors.toList()));
								if (result != null)
									return result;
							}

							if (result == null)
								return null;
						}
					}
				}
			}
		}

		return null;
	}

	private static List<String> toStringList(char[][] object) {
		List<String> returnable = new ArrayList<>();

		for (int i = 0; i < object.length; i++) {
			String value = "";
			for (int j = 0; j < object[i].length; j++) {
				value += object[i][j];
			}
			returnable.add(value);
		}

		return returnable;
	}

	private static char[][] clone(char[][] object) {
		char[][] newMatrix = new char[object.length][object[0].length];

		for (int i = 0; i < object.length; i++) {
			for (int j = 0; j < object[i].length; j++) {
				newMatrix[i][j] = object[i][j];
			}
		}

		return newMatrix;
	}

	@SuppressWarnings("unused")
	private static void print(char[][] object) {

		System.out.println("==============================");
		for (int i = 0; i < object.length; i++) {
			for (int j = 0; j < object[i].length; j++) {
				System.out.print(object[i][j]);
			}
			System.out.println();
		}
		System.out.println("==============================");
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		char[][] crosswordPuzzle = new char[10][10];

		for (int i = 0; i < 10; i++) {
			String line = bufferedReader.readLine();
			crosswordPuzzle[i] = line.toCharArray();
		}

		String words = bufferedReader.readLine();
		List<String> result = Result.crosswordPuzzle(crosswordPuzzle, words);

		bufferedWriter.write(result.stream().collect(joining("\n")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}
