package algorithms.implementation.matrix_layer_rotation;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

	public static void matrixRotation(List<List<Integer>> matrix, int r) {
		List<Queue<Integer>> listConvertedMatrix = convertMatrixToLists(matrix);

		for (Queue<Integer> queue : listConvertedMatrix) {
			for (int i = 0; i < (r % queue.size()); i++) {
				queue.offer(queue.poll());
			}
		}

		printMatrix(matrix, listConvertedMatrix);
	}

	public static void printMatrix(List<List<Integer>> initialMatrix, List<Queue<Integer>> convertedMatrix) {
		int columns = initialMatrix.get(0).size();
		int rows = initialMatrix.size();

		int[][] finalMatrix = new int[rows][columns];

		List<List<Integer>> newConvertedMatrix = convertedMatrix.stream()
				.map(x -> x.stream().collect(Collectors.toList())).collect(Collectors.toList());

		for (int i = 0; i < newConvertedMatrix.size(); i++) {
			for (int j = 0; j < newConvertedMatrix.get(i).size(); j++) {
				int currentValue = newConvertedMatrix.get(i).get(j);

				int columnLimit = columns - (2 * i);
				int rowLimit = (rows - (2 * i) - 2);

				if (j < columnLimit) {
					finalMatrix[i][i + j] = currentValue;
				} else if (j < columnLimit + rowLimit) {
					finalMatrix[i + (j - (columnLimit)) + 1][columns - i - 1] = currentValue;
				} else if (j < 2 * columnLimit + rowLimit) {
					finalMatrix[rows - i - 1][columns - i - (j - (columnLimit + rowLimit)) - 1] = currentValue;
				} else if (j < 2 * columnLimit + 2 * rowLimit) {
					finalMatrix[rows - i - (j - (2 * columnLimit + rowLimit)) - 2][i] = currentValue;
				}
			}
		}

		for (int i = 0; i < finalMatrix.length; i++) {
			for (int j = 0; j < finalMatrix[i].length; j++) {
				System.out.printf("%d ", finalMatrix[i][j]);
			}
			System.out.println();
		}
	}

	public static List<Queue<Integer>> convertMatrixToLists(List<List<Integer>> matrix) {
		int columns = matrix.get(0).size();
		int rows = matrix.size();

		int minNumberOfLists = (Math.min(columns, rows) / 2);

		List<Queue<Integer>> returnable = new ArrayList<>();

		for (int i = 0; i < minNumberOfLists; i++) {
			Deque<Integer> deque = new ArrayDeque<>();

			for (int j = i; j < columns - i - 1; j++) {
				deque.add(matrix.get(i).get(j));
			}

			for (int j = i; j < rows - i - 1; j++) {
				deque.add(matrix.get(j).get(columns - i - 1));
			}

			for (int j = i; j < columns - i - 1; j++) {
				deque.add(matrix.get(rows - i - 1).get(columns - j - 1));
			}

			for (int j = i; j < rows - i - 1; j++) {
				deque.add(matrix.get(rows - j - 1).get(i));
			}

			returnable.add(deque);
		}

		if (columns % 2 == 1 && columns <= rows) {
			Deque<Integer> deque = new ArrayDeque<>();
			for (int j = minNumberOfLists; j < rows - minNumberOfLists; j++) {
				deque.add(matrix.get(j).get(columns - minNumberOfLists - 1));
			}
			returnable.add(deque);
		} else if (rows % 2 == 1 && rows < columns) {
			Deque<Integer> deque = new ArrayDeque<>();
			for (int j = minNumberOfLists; j < columns - minNumberOfLists; j++) {
				deque.add(matrix.get(minNumberOfLists).get(j));
			}
			returnable.add(deque);
		}

		return returnable;
	}
}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int m = Integer.parseInt(firstMultipleInput[0]);

		int n = Integer.parseInt(firstMultipleInput[1]);

		int r = Integer.parseInt(firstMultipleInput[2]);

		List<List<Integer>> matrix = new ArrayList<>();

		IntStream.range(0, m).forEach(i -> {
			try {
				matrix.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		Result.matrixRotation(matrix, r);

		bufferedReader.close();
	}
}
