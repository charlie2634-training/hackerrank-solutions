package implementation.larry_s_array;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

	public static String larrysArray(List<Integer> A) {
		List<Integer> sorted = A.stream().sorted().collect(Collectors.toList());

		for (int i = 0; i < sorted.size(); i++) {
			Integer value = sorted.get(i);
			int indexOfValue = A.indexOf(value);

			if (indexOfValue != i) {
				if ((indexOfValue - i) % 2 == 0) {
					A.remove(value);
					A.add(i, value);
				} else if (i + 2 < A.size()) {
					A.remove(value);
					A.add(i + 1, value);

					Integer previousNumber = A.get(i);
					A.remove(previousNumber);
					A.add(i + 2, previousNumber);
				} else {
					return "NO";
				}
			}
		}
		return "YES";
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
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList());

				String result = Result.larrysArray(A);

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
