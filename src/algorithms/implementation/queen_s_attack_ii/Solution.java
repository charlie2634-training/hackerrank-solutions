package algorithms.implementation.queen_s_attack_ii;

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
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

	enum CardinalDirection {
		NORT(0, 1), NORT_EAST(1, 1), NORT_WEST(-1, 1), SOUTH(0, -1), SOUTH_EAST(1, -1), SOUTH_WEST(-1, -1), EAST(1,
				0), WEST(-1, 0), NONE(0, 0);

		private int xExample;
		private int yExample;

		private CardinalDirection(int xExample, int yExample) {
			this.xExample = xExample;
			this.yExample = yExample;
		}

		public static CardinalDirection from(int x, int y) {
			int xOneBaseCoordinate = x / Math.max(Math.abs(x), 1);
			int yOneBaseCoordinate = y / Math.max(Math.abs(y), 1);

			for (CardinalDirection cardinalDirection : CardinalDirection.values()) {
				if (cardinalDirection.xExample == xOneBaseCoordinate
						&& cardinalDirection.yExample == yOneBaseCoordinate) {
					return cardinalDirection;
				}
			}

			return CardinalDirection.NONE;
		}
	}

	static class BoardCoordinate {
		private int x;
		private int y;
		private CardinalDirection cardinalDirection;

		public BoardCoordinate(int x, int y) {
			this.x = x;
			this.y = y;
			this.cardinalDirection = CardinalDirection.from(x, y);
		}

		@Override
		public String toString() {
			return String.format("[x: %d, y: %d, cardinalDirection: %s]", x, y, cardinalDirection.name());
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public CardinalDirection getCardinalDirection() {
			return cardinalDirection;
		}

		public boolean isClosestToZeroThan(BoardCoordinate boardCoordinate) {
			int boxesUntilZero = Math.abs(x) + Math.abs(y);
			int objectBoxesUntilZero = Math.abs(boardCoordinate.x) + Math.abs(boardCoordinate.y);

			return boxesUntilZero < objectBoxesUntilZero;
		}
	}

	public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
		int positiveLimitInX = n - c_q + 1;
		int positiveLimitInY = n - r_q + 1;
		int negativeLimitInX = -c_q;
		int negativeLimitInY = -r_q;

		Map<CardinalDirection, BoardCoordinate> closestObstacles = new HashMap<>();

		closestObstacles.put(CardinalDirection.NORT, new BoardCoordinate(0, positiveLimitInY));
		closestObstacles.put(CardinalDirection.NORT_EAST, new BoardCoordinate(
				closestTo0(positiveLimitInX, positiveLimitInY), closestTo0(positiveLimitInX, positiveLimitInY)));
		closestObstacles.put(CardinalDirection.NORT_WEST, new BoardCoordinate(
				-closestTo0(negativeLimitInX, positiveLimitInY), closestTo0(negativeLimitInX, positiveLimitInY)));
		closestObstacles.put(CardinalDirection.SOUTH, new BoardCoordinate(0, negativeLimitInY));
		closestObstacles.put(CardinalDirection.SOUTH_EAST, new BoardCoordinate(
				closestTo0(positiveLimitInX, negativeLimitInY), -closestTo0(positiveLimitInX, negativeLimitInY)));
		closestObstacles.put(CardinalDirection.SOUTH_WEST, new BoardCoordinate(
				-closestTo0(negativeLimitInX, negativeLimitInY), -closestTo0(negativeLimitInX, negativeLimitInY)));
		closestObstacles.put(CardinalDirection.EAST, new BoardCoordinate(positiveLimitInX, 0));
		closestObstacles.put(CardinalDirection.WEST, new BoardCoordinate(negativeLimitInX, 0));

		for (List<Integer> obstacle : obstacles) {
			BoardCoordinate obstacleBoardCoordinate = new BoardCoordinate(obstacle.get(1) - c_q, obstacle.get(0) - r_q);

			if (Math.abs(obstacleBoardCoordinate.getX()) == Math.abs(obstacleBoardCoordinate.getY())
					|| obstacleBoardCoordinate.getX() == 0 || obstacleBoardCoordinate.getY() == 0) {

				if (obstacleBoardCoordinate
						.isClosestToZeroThan(closestObstacles.get(obstacleBoardCoordinate.getCardinalDirection()))) {
					closestObstacles.put(obstacleBoardCoordinate.getCardinalDirection(), obstacleBoardCoordinate);
				}

			}
		}

		return closestObstacles.values().stream().mapToInt(x -> Math.max(Math.abs(x.getX()), Math.abs(x.getY())) - 1)
				.sum();
	}

	private static int closestTo0(int x, int y) {
		int absX = Math.abs(x);
		int absY = Math.abs(y);

		return absX < absY ? absX : absY;
	}
}

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int r_q = Integer.parseInt(secondMultipleInput[0]);

		int c_q = Integer.parseInt(secondMultipleInput[1]);

		List<List<Integer>> obstacles = new ArrayList<>();

		IntStream.range(0, k).forEach(i -> {
			try {
				obstacles.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int result = Result.queensAttack(n, k, r_q, c_q, obstacles);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
