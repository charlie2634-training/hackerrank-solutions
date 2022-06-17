package implementation.emma_s_supercomputer;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Result {

	static class Coordinate {
		private int row;
		private int column;

		public Coordinate(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Coordinate))
				return false;
			Coordinate other = (Coordinate) obj;
			return other.row == row && other.column == column;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", row, column);
		}
	}

	private static Map<Integer, List<Coordinate>> crosses;
	private static int maxPossibleCrossLength;

	public static int twoPluses(List<String> grid) {
		crosses = new HashMap<>();
		findAllCrosses(grid);
		return getMaxValue();
	}

	private static void findAllCrosses(List<String> grid) {
		for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(i).length(); j++) {
				if (grid.get(i).charAt(j) == 'G') {
					int maxSize = 1;

					for (int k = 1; k < (grid.get(i).length() / 2) + 1; k++) {
						if (i - k < 0 || grid.get(i - k).charAt(j) != 'G') {
							break;
						}
						if (j - k < 0 || grid.get(i).charAt(j - k) != 'G') {
							break;
						}
						if (i + k >= grid.size() || grid.get(i + k).charAt(j) != 'G') {
							break;
						}
						if (j + k >= grid.get(i).length() || grid.get(i).charAt(j + k) != 'G') {
							break;
						}
						maxSize++;
					}

					for (int k = 1; k <= maxSize; k++) {
						crosses.putIfAbsent(k, new ArrayList<>());
						crosses.get(k).add(new Coordinate(i, j));
					}
				}
			}
		}
		maxPossibleCrossLength = crosses.keySet().stream().mapToInt(x -> x.intValue()).max().getAsInt();
	}

	private static int max = 0;

	private static int getMaxValue() {

		for (int i = maxPossibleCrossLength; i > 0; i--) {
			for (Coordinate currentCoordinate : crosses.get(i)) {
				getNextMax(currentCoordinate, i);
			}
		}

		return max;
	}

	private static void getNextMax(Coordinate currentCoordinate, int currentLength) {
		int currentArea = 1 + (currentLength - 1) * 4;

		for (int j = currentLength; j > 0; j--) {
			int otherArea = 1 + (j - 1) * 4;
			int newPossibleMax = currentArea * otherArea;

			if (newPossibleMax > max) {
				Set<Coordinate> otherCrosses = crosses.get(j).stream().filter(x -> !x.equals(currentCoordinate))
						.collect(Collectors.toSet());

				for (Coordinate otherCoordinate : otherCrosses) {
					if (!overlaps(currentCoordinate, currentLength, otherCoordinate, j)) {
						max = newPossibleMax;
					}
				}
			}
		}
	}

	private static boolean overlaps(Coordinate a, int lengthA, Coordinate b, int lengthB) {
		int minAX = a.getColumn() - (lengthA - 1);
		int maxAX = a.getColumn() + (lengthA - 1);
		int minAY = a.getRow() - (lengthA - 1);
		int maxAY = a.getRow() + (lengthA - 1);

		int minBX = b.getColumn() - (lengthB - 1);
		int maxBX = b.getColumn() + (lengthB - 1);
		int minBY = b.getRow() - (lengthB - 1);
		int maxBY = b.getRow() + (lengthB - 1);

		if (minAX <= b.getColumn() && b.getColumn() <= maxAX && minBY <= a.getRow() && a.getRow() <= maxBY) {
			return true;
		}

		if (minBX <= a.getColumn() && a.getColumn() <= maxBX && minAY <= b.getRow() && b.getRow() <= maxAY) {
			return true;
		}

		if (a.getRow() == b.getRow()) {
			if (minAX <= maxBX && maxBX <= maxAX)
				return true;
			if (minAX <= minBX && minBX <= maxAX)
				return true;

			if (minBX <= maxAX && maxAX <= maxBX)
				return true;
			if (minBX <= minAX && minAX <= maxBX)
				return true;
		}

		if (a.getColumn() == b.getColumn()) {
			if (minAY <= maxBY && maxBY <= maxAY)
				return true;
			if (minAY <= minBY && minBY <= maxAY)
				return true;

			if (minBY <= maxAY && maxAY <= maxBY)
				return true;
			if (minBY <= minAY && minAY <= maxBY)
				return true;
		}

		return false;
	}

}

public class Solution {
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int m = Integer.parseInt(firstMultipleInput[1]);

		List<String> grid = IntStream.range(0, n).mapToObj(i -> {
			try {
				return bufferedReader.readLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(toList());

		int result = Result.twoPluses(grid);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
