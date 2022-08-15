package one_week_preparation_kit.day_b.counting_sort_a;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Result {

    public static List<Integer> countingSort(List<Integer> arr) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for(int i = 0; i < 100; i++) occurrences.put(i, 0);
        
        for(Integer value : arr) {
            occurrences.put(value, occurrences.get(value) + 1);
        }
        
        return occurrences.values().stream().collect(Collectors.toList());
    }

}

public class Solution {
    @SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.countingSort(arr);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining(" "))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}