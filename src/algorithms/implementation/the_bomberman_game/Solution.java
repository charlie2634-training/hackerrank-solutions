package algorithms.implementation.the_bomberman_game;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Result {

	public static List<String> bomberMan(int n, List<String> grid) {
		if (n == 1)
			return grid;
		if (n % 2 == 0) {
			List<String> returnable = new ArrayList<>();
			for (int row = 0; row < grid.size(); row++) {
				returnable.add(grid.get(row).replace('.', 'O'));
			}
			return returnable;
		}

		int columns = grid.get(0).length();
		int rows = grid.size();
		int columnsXRows = columns * rows;

		HashMap<Boolean, Set<Integer>> bombs = new HashMap<>();
		bombs.put(Boolean.TRUE, new HashSet<>());
		bombs.put(Boolean.FALSE, new HashSet<>());

		for (int row = 0; row < grid.size(); row++) {
			for (int column = 0; column < grid.get(row).length(); column++) {
				Boolean group = Boolean.TRUE.equals(grid.get(row).charAt(column) == 'O');
				bombs.get(group).add(row * grid.get(row).length() + column);
			}
		}

		int numberOfIterations = 1;
		if ((n - 3) % 4 != 0)
			numberOfIterations++;

		for (int second = 0; second < numberOfIterations; second++) {
			Set<Integer> currentActives = bombs.get(Boolean.TRUE);
			Set<Integer> inactivesActives = bombs.get(Boolean.FALSE);

			for (int active : new HashSet<>(currentActives)) {
				if (active - 1 >= 0 && active % columns != 0) {
					currentActives.add(active - 1);
					inactivesActives.remove(active - 1);
				}
				if (active + 1 < columnsXRows && (active + 1) % columns != 0) {
					currentActives.add(active + 1);
					inactivesActives.remove(active + 1);
				}
				if (active - columns >= 0) {
					currentActives.add(active - columns);
					inactivesActives.remove(active - columns);
				}
				if (active + columns < columnsXRows) {
					currentActives.add(active + columns);
					inactivesActives.remove(active + columns);
				}

				// System.out.printf("evaluating [%d] ---- [%d] [%d] [%d]
				// [%d]\n", active, active - 1, active + 1, active - columns,
				// active + columns);

			}

			bombs.put(Boolean.TRUE, inactivesActives);
			bombs.put(Boolean.FALSE, currentActives);
		}

		return getSerialized(bombs, rows, columns);
	}

	private static List<String> getSerialized(HashMap<Boolean, Set<Integer>> bombs, int rows, int columns) {
		Set<Integer> currentActives = bombs.get(Boolean.TRUE);

		String returnable = "";

		for (int i = 0; i < rows * columns; i++) {
			if (currentActives.contains(i)) {
				returnable += "O";
			} else {
				returnable += ".";
			}
		}
		List<String> returnableList = new ArrayList<>();

		for (int i = 0; i < rows; i++) {
			returnableList.add(i, returnable.substring(i * columns, i * columns + columns));
		}

		return returnableList;
	}
}

public class Solution {
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int r = Integer.parseInt(firstMultipleInput[0]);

		int c = Integer.parseInt(firstMultipleInput[1]);

		int n = Integer.parseInt(firstMultipleInput[2]);

		List<String> grid = IntStream.range(0, r).mapToObj(i -> {
			try {
				return bufferedReader.readLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(toList());

		List<String> result = Result.bomberMan(n, grid);

		bufferedWriter.write(result.stream().collect(joining("\n")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}
