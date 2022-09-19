package one_week_preparation_kit.day_e.balanced_brackets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.IntStream;

class Result {
	
	public static String isBalanced(String s) {
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < s.length(); i++) {
			char currentCharacter = s.charAt(i);

			switch (currentCharacter) {
			case '{':
			case '[':
			case '(':
				stack.add(currentCharacter);
				break;
			case '}':
				if(stack.size() == 0 || !stack.pop().equals('{')) return "NO";
				break;
			case ']':
				if(stack.size() == 0 || !stack.pop().equals('[')) return "NO";
				break;
			case ')':
				if(stack.size() == 0 || !stack.pop().equals('(')) return "NO";
				break;
			default:
				return "NO";
			}
		}
		return stack.size() == 0 ? "YES" : "NO";
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
				String s = bufferedReader.readLine();

				String result = Result.isBalanced(s);

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
