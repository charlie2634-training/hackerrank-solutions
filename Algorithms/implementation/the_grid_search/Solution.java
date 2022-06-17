package implementation.the_grid_search;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

class Result {

	/*
	 * Complete the 'gridSearch' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * following parameters: 1. STRING_ARRAY G 2. STRING_ARRAY P
	 */

	public static String gridSearch(List<String> G, List<String> P) {
		char firstChar = P.get(0).charAt(0);

		for (int i = 0; i < G.size() - (P.size() - 1); i++) {
			for (int j = 0; j < G.get(i).length() - (P.get(0).length() - 1); j++) {

				char currentChar = G.get(i).charAt(j);

				if (firstChar == currentChar) {
					boolean valid = true;

					for (int k = 0; k < P.size(); k++) {
						if (!G.get(i + k).substring(j, j + P.get(k).length()).equals(P.get(k))) {
							valid = false;
						}
					}

					if (valid)
						return "YES";
				}

			}
		}

		return "NO";
	}

}

public class Solution {
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				int R = Integer.parseInt(firstMultipleInput[0]);

				int C = Integer.parseInt(firstMultipleInput[1]);

				List<String> G = IntStream.range(0, R).mapToObj(i -> {
					try {
						return bufferedReader.readLine();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}).collect(toList());

				String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				int r = Integer.parseInt(secondMultipleInput[0]);

				int c = Integer.parseInt(secondMultipleInput[1]);

				List<String> P = IntStream.range(0, r).mapToObj(i -> {
					try {
						return bufferedReader.readLine();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}).collect(toList());

				String result = Result.gridSearch(G, P);

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
