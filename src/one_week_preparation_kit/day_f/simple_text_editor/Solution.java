package one_week_preparation_kit.day_f.simple_text_editor;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int numberOfOperations = Integer.parseInt(bufferedReader.readLine());
		Stack<String> stack = new Stack<>();
		stack.add("");

		for (int i = 0; i < numberOfOperations; i++) {
			String[] operation = bufferedReader.readLine().split(" ");

			switch (operation[0]) {
			case "1":
				stack.add(stack.peek() + operation[1]);
				break;
			case "2":
				stack.add(stack.peek().substring(0, stack.peek().length() - Integer.valueOf(operation[1])));
				break;
			case "3":
				bufferedWriter.append(stack.peek().charAt(Integer.valueOf(operation[1]) - 1));
				bufferedWriter.newLine();
				break;
			case "4":
				stack.pop();
				break;
			}
		}

		bufferedReader.close();
		bufferedWriter.close();
	}
}