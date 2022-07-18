package algorithms.recursion.the_power_sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Result {

    /*
     * Complete the 'powerSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER X
     *  2. INTEGER N
     */

	private static Map<Integer, Integer> poweredValues = new HashMap<>();
	private static int targetValue;
	
    public static int powerSum(int X, int N) {
    	targetValue = X;
    	
    	for(int i = 1; ; i++) {
    		Integer poweredValue = (int) Math.pow(i, N);
    		
    		if (poweredValue > X) {
    			break;
    		}

			poweredValues.put(i, poweredValue);
    	}
    	
    	return numberOfPossibleSums(1, 0);
    }
    
    private static int numberOfPossibleSums(Integer index, Integer accumulated) {
    	Integer count = 0;
    	
    	for(;index <= poweredValues.size(); index++) {
    		int value = poweredValues.get(index);
    		
    		if(value + accumulated == targetValue) {
    			count++;
    		} else if(value + accumulated < targetValue) {
    			count += numberOfPossibleSums(index + 1, value + accumulated);
    		} else {
    			break;
    		}
    	}
    	
    	return count;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int X = Integer.parseInt(bufferedReader.readLine().trim());

        int N = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.powerSum(X, N);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
