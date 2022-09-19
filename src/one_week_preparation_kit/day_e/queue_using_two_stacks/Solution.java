package one_week_preparation_kit.day_e.queue_using_two_stacks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int numberOfQueries = scanner.nextInt();
		scanner.nextLine();
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < numberOfQueries; i++) {
			String line = scanner.nextLine();
			String[] lineSplitted = line.split(" ");
			int operation = Integer.valueOf(lineSplitted[0]);

			switch (operation) {
			case 1:
				int value = Integer.valueOf(lineSplitted[1]);
				queue.add(value);
				break;
			case 2:
				queue.poll();
				break;
			case 3:
				bufferedWriter.write(String.valueOf(queue.peek()));
				bufferedWriter.newLine();
				break;
			}
		}

		bufferedWriter.close();

		scanner.close();
	}
}
