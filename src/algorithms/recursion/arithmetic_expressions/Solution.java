package algorithms.recursion.arithmetic_expressions;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

class Result {

    private static final int MODULUS = 101;
    private static Map<Integer, List<Integer>> alreadyVisitedIndexes;

    public static String arithmeticExpressions(List<Integer> arr) {
        Map<String, Integer> currentValues = new HashMap<>();
        alreadyVisitedIndexes = new HashMap<>();
        
        alreadyVisitedIndexes.put(0, Arrays.asList(arr.get(0)));
        currentValues.put(String.valueOf(arr.get(0)), arr.get(0) % MODULUS);

        for (int i = 1; i < arr.size(); i++) {
            alreadyVisitedIndexes.put(i, new ArrayList<>());
            Map<String, Integer> newValues = new HashMap<>();
            Integer nextValue = arr.get(i);

            for (Entry<String, Integer> currentValue : currentValues.entrySet()) {
                newValues.putAll(getPossibleSubNodes(i, currentValue, nextValue));
            }

            for (Entry<String, Integer> newValue : newValues.entrySet()) {
                if (newValue.getValue() == 0) {

                    String result = newValue.getKey();

                    for (int j = i + 1; j < arr.size(); j++) {
                        result += "*" + arr.get(j);
                    }

                    return result;
                }
            }

            currentValues = newValues;
        }

        return "";
    }

    public static Map<String, Integer> getPossibleSubNodes(Integer index, Entry<String, Integer> currentNode,
            Integer nextValue) {
        Map<String, Integer> subNodes = new HashMap<>();

        for (int i = 1; i <= 3; i++) {
            String newKey = "";
            Integer newValue = null;
            switch (i) {
            case 1:
                newValue = currentNode.getValue() + nextValue;
                newKey = currentNode.getKey() + "+" + nextValue;
                break;
            case 2:
                newValue = currentNode.getValue() * nextValue;
                newKey = currentNode.getKey() + "*" + nextValue;
                break;
            case 3:
                newValue = currentNode.getValue() - nextValue;
                newKey = currentNode.getKey() + "-" + nextValue;
                break;
            }

            if(!alreadyVisitedIndexes.get(index).contains(newValue % MODULUS)) {
                subNodes.put(newKey, newValue % MODULUS);
                alreadyVisitedIndexes.get(index).add(newValue % MODULUS);
            }
            
        }

        return subNodes;
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

        String result = Result.arithmeticExpressions(arr);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
