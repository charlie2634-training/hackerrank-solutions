package algorithms.search.connected_cells_in_a_grid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

class Result {

	private static int[][] matrix;

	public static int connectedCell(int width, int height, int[][] current) {
		matrix = current;

		int max = 0;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int currentValue = evaluatePoint(i, j);

				if (currentValue > max) {
					max = currentValue;
				}
			}
		}

		return max;
	}

	public static int evaluatePoint(int row, int column) {
		if (row < 0)
			return 0;
		if (column < 0)
			return 0;
		if (row >= matrix.length)
			return 0;
		if (column >= matrix[0].length)
			return 0;
		if (matrix[row][column] == -1)
			return 0;
		if (matrix[row][column] == 0)
			return 0;

		matrix[row][column] = -1;
		int sum = 1;

		sum += evaluatePoint(row + 1, column);
		sum += evaluatePoint(row - 1, column);
		sum += evaluatePoint(row, column + 1);
		sum += evaluatePoint(row, column - 1);
		sum += evaluatePoint(row + 1, column + 1);
		sum += evaluatePoint(row + 1, column - 1);
		sum += evaluatePoint(row - 1, column + 1);
		sum += evaluatePoint(row - 1, column - 1);

		return sum;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		int m = Integer.parseInt(bufferedReader.readLine().trim());

		int[][] matrix = new int[n][];

		for (int i = 0; i < n; i++) {
			matrix[i] = Stream.of(bufferedReader.readLine().trim().split(" ")).mapToInt(x -> Integer.parseInt(x))
					.toArray();
		}

		int result = Result.connectedCell(n, m, matrix);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
