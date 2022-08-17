package one_week_preparation_kit.day_d.truck_tour;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'truckTour' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * 2D_INTEGER_ARRAY petrolpumps as parameter.
	 */

	public static int truckTour(List<Integer> pumps) {
		first: for (int i = 0; i < pumps.size(); i++) {
			int accumulated = pumps.get(i);
			if (accumulated <= 0)
				continue first;

			for (int j = (i + 1) % pumps.size(), k = 0; k < pumps.size() - 1; j = (j + 1) % pumps.size(), k++) {
				accumulated += pumps.get(j);
				if (accumulated <= 0)
					continue first;
			}

			return i;
		}
		return 0;
	}

}

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> petrolpumps = new ArrayList<>();

		IntStream.range(0, n).forEach(i -> {
			try {
				List<Integer> values = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList());

				petrolpumps.add(values.get(0) - values.get(1));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int result = Result.truckTour(petrolpumps);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
