package data_structures.java_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		List<Integer> vector = new ArrayList<>();

		int numberOfElements = scanner.nextInt();

		for (int j = 0; j < numberOfElements; j++) {
			vector.add(scanner.nextInt());
		}

		int numberOfQueries = scanner.nextInt();

		for (int i = 0; i < numberOfQueries; i++) {
			String operation = scanner.next();

			if (operation.equals("Insert")) {
				int index = scanner.nextInt();
				int value = scanner.nextInt();

				vector.add(index, value);

			} else if (operation.equals("Delete")) {
				int index = scanner.nextInt();
				vector.remove(index);
			}
		}

		System.out.println(String.join(" ", vector.stream().map(x -> x.toString()).collect(Collectors.toList())));

		scanner.close();
	}
}
