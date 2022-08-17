package one_week_preparation_kit.day_a.find_the_median;

import java.util.Collections;
import java.util.List;

class Result {
	/*
	 * Complete the 'findMedian' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */
	public static int findMedian(List<Integer> arr) {
		Collections.sort(arr);
		if (arr.size() % 2 == 1) {
			return arr.get(arr.size() / 2);
		} else {
			return (arr.get(arr.size() / 2) + arr.get(arr.size() / 2 + 1)) / 2;
		}
	}
}

public class Solution {

}
