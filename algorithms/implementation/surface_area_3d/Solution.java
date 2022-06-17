package implementation.surface_area_3d;

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

	private static List<List<Integer>> values;
	private static int columns;
	private static int rows;

	public static int surfaceArea(List<List<Integer>> A) {
		values = A;
		columns = values.get(0).size();
		rows = values.size();

		int topAndBottomArea = A.size() * A.get(0).size() * 2;

		int accumulatedValue = topAndBottomArea;

		for (int i = -1; i < rows + 1; i++) {
			for (int j = -1; j < columns + 1; j++) {
				int currentValue = getValue(i, j);
				int nextXValue = getValue(i + 1, j);
				int nextYValue = getValue(i, j + 1);

				accumulatedValue += Math.abs(currentValue - nextXValue);
				accumulatedValue += Math.abs(currentValue - nextYValue);
			}
		}

		return accumulatedValue;
	}

	private static int getValue(int i, int j) {
		if (i < 0 || i >= rows)
			return 0;
		if (j < 0 || j >= columns)
			return 0;
		return values.get(i).get(j);
	}
}

public class Solution {
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int H = Integer.parseInt(firstMultipleInput[0]);

		int W = Integer.parseInt(firstMultipleInput[1]);

		List<List<Integer>> A = new ArrayList<>();

		IntStream.range(0, H).forEach(i -> {
			try {
				A.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
						.collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int result = Result.surfaceArea(A);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
