package implementation.forming_a_magic_square;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

	private final static List<Integer> magicSquareEdges = Arrays.asList(8, 3, 4, 9, 2, 7, 6, 1);

	public static int formingMagicSquare(List<List<Integer>> matrix) {
		int coreCost = Math.abs(matrix.get(1).get(1) - 5);
		matrix.get(1).set(1, 5);

		List<Integer> costs = new ArrayList<>();

		List<Integer> edgePoints = getEdgePoints(matrix);

		for (int i = 0; i < edgePoints.size(); i = i + 2) {
			int edgeCost = getCostFrom(i, edgePoints, false);
			costs.add(coreCost + edgeCost);
			edgeCost = getCostFrom(i, edgePoints, true);
			costs.add(coreCost + edgeCost);
		}

		System.out.println(costs);
		return costs.stream().mapToInt(x -> x.intValue()).min().getAsInt();
	}

	private static int getCostFrom(int startPoint, List<Integer> edgePoints, boolean reversed) {
		int cost = 0;

		System.out.println("=======================================");
		for (int i = 0; i < magicSquareEdges.size(); i++) {
			if (reversed) {
				int currentPoint = (startPoint - i + 8) % 8;

				System.out.printf("Cost of converting %d in %d = %d \n", edgePoints.get(currentPoint),
						magicSquareEdges.get(i), Math.abs(edgePoints.get(currentPoint) - magicSquareEdges.get(i)));

				cost += Math.abs(edgePoints.get(currentPoint) - magicSquareEdges.get(i));
			} else {
				int currentPoint = (startPoint + i) % 8;

				System.out.printf("Cost of converting %d in %d = %d \n", edgePoints.get(currentPoint),
						magicSquareEdges.get(i), Math.abs(edgePoints.get(currentPoint) - magicSquareEdges.get(i)));

				cost += Math.abs(edgePoints.get(currentPoint) - magicSquareEdges.get(i));
			}
		}

		System.out.println("Total cost: " + cost);
		return cost;
	}

	private static List<Integer> getEdgePoints(List<List<Integer>> matrix) {
		List<Integer> edgePoints = new ArrayList<>();

		edgePoints.addAll(matrix.get(0));
		edgePoints.add(matrix.get(1).get(2));
		for (int i = 0; i < matrix.get(2).size(); i++)
			edgePoints.add(matrix.get(2).get(matrix.get(2).size() - (i + 1)));
		edgePoints.add(matrix.get(1).get(0));

		return edgePoints;
	}
}

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		List<List<Integer>> s = new ArrayList<>();

		IntStream.range(0, 3).forEach(i -> {
			try {
				s.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
						.collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int result = Result.formingMagicSquare(s);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
