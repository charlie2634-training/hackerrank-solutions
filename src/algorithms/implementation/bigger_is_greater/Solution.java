package algorithms.implementation.bigger_is_greater;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Result {

	public static String biggerIsGreater(String w) {
		List<Integer> values = w.chars().boxed().collect(Collectors.toList());
		Map<Integer, Integer> nextLocations = new HashMap<>();

		for (int i = values.size() - 1; i >= 0; i--) {
			int currentCharacter = values.get(i);

			for (int nextCharacter = currentCharacter + 1; nextCharacter <= 122; nextCharacter++) {
				if (nextLocations.containsKey(nextCharacter)) {
					values.set(i, nextCharacter);
					values.set(nextLocations.get(nextCharacter), currentCharacter);

					List<Integer> firstSubset = values.subList(0, i + 1);
					List<Integer> secondSusbset = values.subList(i + 1, values.size());
					Collections.sort(secondSusbset);

					values = firstSubset;
					values.addAll(secondSusbset);

					return values.stream().map(x -> String.valueOf((char) x.intValue())).reduce((a, b) -> a.concat(b))
							.get();
				}
			}

			nextLocations.put(currentCharacter, i);
		}
		return "no answer";
	}
}

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int T = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, T).forEach(TItr -> {
			try {
				String w = bufferedReader.readLine();

				String result = Result.biggerIsGreater(w);

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
