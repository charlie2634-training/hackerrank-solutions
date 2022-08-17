package one_week_preparation_kit.day_d.grid_challenge;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Result {

	/*
	 * Complete the 'gridChallenge' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * STRING_ARRAY grid as parameter.
	 */

	public static String gridChallenge(List<String> grid) {
		char[][] array = new char[grid.size()][];

		for (int i = 0; i < grid.size(); i++) {
			char[] chars = grid.get(i).toCharArray();
			Arrays.sort(chars);
			array[i] = chars;
		}

		Map<Integer, Character> lastChecked = new HashMap<>();

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (lastChecked.containsKey(j)) {
					Character previousValue = lastChecked.get(j);
					Character currentValue = array[i][j];

					if (previousValue.compareTo(currentValue) > 0) {
						return "NO";
					}
				}

				lastChecked.put(j, array[i][j]);
			}
		}

		return "YES";
	}

}

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<String> grid = IntStream.range(0, n).mapToObj(i -> {
					try {
						return bufferedReader.readLine();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}).collect(toList());

				String result = Result.gridChallenge(grid);

				bufferedWriter.write(result);
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
