package algorithms.recursion.bowling_pins;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Result {
    
    private static Map<Integer, Integer> grundyNumbers = new HashMap<>();

    public static String isWinning(int n, String config) {        
        List<Integer> splittedConfig = Arrays.asList(config.split("X")).stream().filter(x -> !x.isEmpty()).map(x -> x.length()).collect(Collectors.toList());
        List<Integer> grundyNumbers = splittedConfig.stream().map(Result::getGrundyNumber).collect(Collectors.toList());

        if(grundyNumbers.size() == 1) {
            return grundyNumbers.get(0) == 0 ? "LOSE" : "WIN";
        } else if(grundyNumbers.size() > 1){
            Integer grundyNumber = grundyNumbers.stream().reduce((x, y) -> x ^ y).get();
            return grundyNumber == 0 ? "LOSE" : "WIN";
        }
        
        return "LOSE";
    }
    
    public static Integer getGrundyNumber(Integer value) {
        Integer returnable = null;
        Set<Integer> nextStatesGrundyNumbers = new HashSet<>();
        
        if(!grundyNumbers.containsKey(value)) {
            if(value == 0) {
                returnable = 0;
            } else if(value == 1) {
                returnable = 1;
            } else if(value == 2) {
                returnable = 2;
            } else {
                int newValueA = value - 1;
                int newValueB = value - 2;
                
                nextStatesGrundyNumbers.add(getGrundyNumber(newValueA));
                nextStatesGrundyNumbers.add(getGrundyNumber(newValueB));
                for(int i = 1; i < newValueA; i++) {
                    int firstPart = i;
                    int secondPart = newValueA - i;

                    int firstPartGrundyNumber = getGrundyNumber(firstPart);
                    int secondPartGrundyNumber = getGrundyNumber(secondPart);
                    nextStatesGrundyNumbers.add(firstPartGrundyNumber ^ secondPartGrundyNumber);
                }
                for(int i = 1; i < newValueB; i++) {
                    int firstPart = i;
                    int secondPart = newValueB - i;

                    int firstPartGrundyNumber = getGrundyNumber(firstPart);
                    int secondPartGrundyNumber = getGrundyNumber(secondPart);
                    nextStatesGrundyNumbers.add(firstPartGrundyNumber ^ secondPartGrundyNumber);
                }
                returnable = getMex(nextStatesGrundyNumbers);            
            }
            
            grundyNumbers.put(value, returnable);    
        }
        
        
        return grundyNumbers.get(value);
    }
    
    private static int getMex(Set<Integer> grundyNumbers) {
        int mex = 0;

        while (grundyNumbers.contains(mex)) {
            mex++;
        }

        return mex;
    }
}

public class Solution {
    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                String config = bufferedReader.readLine();

                String result = Result.isWinning(n, config);

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
