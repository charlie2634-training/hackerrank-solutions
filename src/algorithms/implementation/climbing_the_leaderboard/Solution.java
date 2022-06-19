package algorithms.implementation.climbing_the_leaderboard;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class Result {

	/*
	 * Complete the 'climbingLeaderboard' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY. The function accepts
	 * following parameters: 1. INTEGER_ARRAY ranked 2. INTEGER_ARRAY player
	 */

	public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
		List<Integer> positions = new ArrayList<>();

		int currentPosition = 1;
		int playerIndex = 0;
		int previousNumber = Integer.MAX_VALUE;

		for (int i = 0; i < ranked.size(); i++) {

			for (int j = playerIndex; j < player.size(); j++) {
				if (player.get(player.size() - (playerIndex + 1)) >= ranked.get(i)
						&& player.get(player.size() - (playerIndex + 1)) < previousNumber) {
					positions.add(currentPosition);
					playerIndex++;
				} else {
					break;
				}
			}

			if (previousNumber != ranked.get(i)) {
				currentPosition++;
				previousNumber = ranked.get(i);
			}

		}

		for (int i = playerIndex; i < player.size(); i++) {
			positions.add(currentPosition);
		}

		Collections.sort(positions, (x, y) -> y.compareTo(x));

		return positions;
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		List<Integer> result = Result.climbingLeaderboard(ranked, player);

		bufferedWriter.write(result.stream().map(Object::toString).collect(joining("\n")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}
