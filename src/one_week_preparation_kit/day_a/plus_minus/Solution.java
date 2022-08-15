package one_week_preparation_kit.day_a.plus_minus;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Result {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    enum Type {
        POSITIVE,
        NEGATIVE,
        ZERO
    }


    public static void plusMinus(List<Integer> arr) {
        int total = arr.size();
        Map<Type, Double> amountPerType = new HashMap<>();

        amountPerType.put(Type.POSITIVE, 0.0);
        amountPerType.put(Type.NEGATIVE, 0.0);
        amountPerType.put(Type.ZERO, 0.0);
        
        for(Integer value : arr) {
        	Type type = Type.ZERO;
        	
        	if(value < 0) type = Type.NEGATIVE;
        	else if (value > 0) type = Type.POSITIVE;
        	
    		amountPerType.put(type, amountPerType.get(type) + 1);
        }

        System.out.println(amountPerType.get(Type.POSITIVE) / total);
        System.out.println(amountPerType.get(Type.NEGATIVE) / total);
        System.out.println(amountPerType.get(Type.ZERO) / total);
    }

}

public class Solution {
    @SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.plusMinus(arr);

        bufferedReader.close();
    }
}
