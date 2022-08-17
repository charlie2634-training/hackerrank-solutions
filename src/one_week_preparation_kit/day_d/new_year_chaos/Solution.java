package one_week_preparation_kit.day_d.new_year_chaos;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

	public static void minimumBribes(List<Integer> currentQueue) {
		int numberOfBribes = 0;

		for (int expectedValue = currentQueue.size(); expectedValue > 1; expectedValue--) {
			int actualValue = currentQueue.get(expectedValue - 1);

			if (actualValue < expectedValue) {
				Integer actualIndexOfExpectedValue = null;

				for (int i = 1; i <= 2; i++) {
					int previousIndex = expectedValue - 1 - i;

					if (previousIndex >= 0 && currentQueue.get(previousIndex).equals(expectedValue)) {
						actualIndexOfExpectedValue = previousIndex;
						numberOfBribes += i;
						currentQueue.remove(previousIndex);
					}
				}

				if (actualIndexOfExpectedValue == null) {
					System.out.println("Too chaotic");
					return;
				}
			}
		}

		System.out.println(numberOfBribes);
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList());

				Result.minimumBribes(q);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
	}
}
