package implementation.organizing_containers_of_balls;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'organizingContainers' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * 2D_INTEGER_ARRAY container as parameter.
	 */

	public static String organizingContainers(List<List<Integer>> container) {

		List<BigInteger> rows = new ArrayList<>();
		Map<Integer, BigInteger> columns = new HashMap<>();

		for (int i = 0; i < container.size(); i++) {
			BigInteger valueOfRow = new BigInteger("0");

			for (int j = 0; j < container.get(i).size(); j++) {
				valueOfRow = valueOfRow.add(BigInteger.valueOf(container.get(i).get(j)));
				columns.putIfAbsent(j, new BigInteger("0"));
				columns.put(j, columns.get(j).add(BigInteger.valueOf(container.get(i).get(j))));
			}

			rows.add(valueOfRow);
		}

		Set<String> first = columns.values().stream().map(x -> x.toString()).collect(Collectors.toSet());
		Set<String> second = rows.stream().map(x -> x.toString()).collect(Collectors.toSet());

		if (first.equals(second)) {
			return "Possible";
		} else {
			return "Impossible";
		}
	}
}

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<List<Integer>> container = new ArrayList<>();

				IntStream.range(0, n).forEach(i -> {
					try {
						container.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
								.map(Integer::parseInt).collect(toList()));
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				});

				String result = Result.organizingContainers(container);

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
