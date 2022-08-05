package algorithms.search.pairs;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

class Result {

    /*
     * Complete the 'pairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

    
    public static int pairs(int k, List<Integer> arr) {
        Map<Integer, Integer> values = new HashMap<>();
        
        for(Integer arrValue : arr) {
            values.putIfAbsent(arrValue, 0);
            values.putIfAbsent(arrValue + k, 0);

            values.put(arrValue, values.get(arrValue) + 1);
            values.put(arrValue + k, values.get(arrValue + k) + 1);
        }
        
        int count = 0;
        for(Entry<Integer, Integer> value : values.entrySet()) {
            if(value.getValue() > 1) {
                count++;
            }
        }
        return count;
    }

}

public class Solution {
    @SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.pairs(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}