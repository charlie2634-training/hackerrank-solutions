package one_week_preparation_kit.day_b.lonely_integer;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Result {

    public static int lonelyinteger(List<Integer> a) {
    	Map<Integer, Integer> occurrences = new HashMap<>();
    	
    	for(Integer value : a) {
    		occurrences.putIfAbsent(value, 0);
    		occurrences.put(value, occurrences.get(value) + 1);
    	}
    	
    	return occurrences.entrySet().stream().filter(x -> x.getValue().equals(1)).findFirst().orElse(null).getKey();
    }

}

public class Solution {
    @SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.lonelyinteger(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
