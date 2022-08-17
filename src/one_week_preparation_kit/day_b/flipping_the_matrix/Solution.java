package one_week_preparation_kit.day_b.flipping_the_matrix;

import java.util.List;

class Result {
	/*
	 * Complete the 'flippingMatrix' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * 2D_INTEGER_ARRAY matrix as parameter.
	 */
	public static int flippingMatrix(List<List<Integer>> matrix) {
		int size = matrix.size() / 2;
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int upperLeftValue = matrix.get(i).get(j);
				int upperRightValue = matrix.get(i).get(matrix.get(i).size() - (j + 1));
				int lowerLeftValue = matrix.get(matrix.size() - (i + 1)).get(j);
				int lowerRightValue = matrix.get(matrix.size() - (i + 1)).get(matrix.get(i).size() - (j + 1));
				int max = upperLeftValue;
				if (upperRightValue > max)
					max = upperRightValue;
				if (lowerLeftValue > max)
					max = lowerLeftValue;
				if (lowerRightValue > max)
					max = lowerRightValue;
				count += max;
				System.out.println(max);
			}
		}
		return count;
	}
}

public class Solution {

}
