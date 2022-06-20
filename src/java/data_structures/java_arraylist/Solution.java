package java.data_structures.java_arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<List<Integer>> matrix = new ArrayList<>();

		int numberOfArrays = scanner.nextInt();

		for (int i = 0; i < numberOfArrays; i++) {
			List<Integer> vector = new ArrayList<>();

			int numberOfElements = scanner.nextInt();

			for (int j = 0; j < numberOfElements; j++) {
				vector.add(scanner.nextInt());
			}
			matrix.add(vector);
		}

		int numberOfQueries = scanner.nextInt();

		for (int i = 0; i < numberOfQueries; i++) {
			int y = scanner.nextInt() - 1;
			int x = scanner.nextInt() - 1;

			if (y < matrix.size() && x < matrix.get(y).size()) {
				System.out.println(matrix.get(y).get(x));
			} else {
				System.out.println("ERROR!");
			}
		}

		scanner.close();
	}
}
