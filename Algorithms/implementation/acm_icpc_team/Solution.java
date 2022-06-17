package implementation.acm_icpc_team;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Result {

	public static List<Integer> acmTeam(List<String> topic) {
		List<BigInteger> topicIntegerValues = topic.stream().map(x -> new BigInteger(x, 2))
				.collect(Collectors.toList());

		Map<Integer, Integer> results = new HashMap<>();

		for (int i = 0; i < topicIntegerValues.size(); i++) {
			BigInteger first = topicIntegerValues.get(i);

			for (int j = i + 1; j < topicIntegerValues.size(); j++) {
				BigInteger second = topicIntegerValues.get(j);

				int numberOfOnes = first.or(second).toString(2).replace("0", "").length();

				results.putIfAbsent(numberOfOnes, 0);
				results.put(numberOfOnes, results.get(numberOfOnes) + 1);
			}
		}

		int maximum = results.keySet().stream().mapToInt(x -> x.intValue()).max().orElse(0);

		return Arrays.asList(maximum, results.get(maximum));
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

		List<String> topic = IntStream.range(0, n).mapToObj(i -> {
			try {
				return bufferedReader.readLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(toList());

		List<Integer> result = Result.acmTeam(topic);

		bufferedWriter.write(result.stream().map(Object::toString).collect(joining("\n")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}
