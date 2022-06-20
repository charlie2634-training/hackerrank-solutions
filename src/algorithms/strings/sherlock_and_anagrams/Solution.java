package algorithms.strings.sherlock_and_anagrams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Result {

	public static int sherlockAndAnagrams(String s) {
		Map<String, Integer> count = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				String substring = s.substring(i, j + 1);

				char[] chars = substring.toCharArray();
				Arrays.sort(chars);
				String sorted = new String(chars);

				count.putIfAbsent(sorted, 0);
				count.put(sorted, count.get(sorted) + 1);
			}
		}

		int counter = 0;
		
		for(String key : count.keySet()) {
			int numberOfElements = count.get(key);
			
			for(int i = 1; i < numberOfElements; i++) {
				counter += i;
			}
		}
		
		return counter;
	}

}

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				String s = bufferedReader.readLine();

				int result = Result.sherlockAndAnagrams(s);

				bufferedWriter.write(String.valueOf(result));
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
