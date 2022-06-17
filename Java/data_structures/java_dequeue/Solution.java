package data_structures.java_dequeue;

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Deque<Integer> deque = new ArrayDeque<>();
		int n = in.nextInt();
		int m = in.nextInt();

		long max = 0;
		long previousCount = 0;

		for (int i = 0; i < n; i++) {
			int insertedNumber = in.nextInt();

			if (i + 1 > m) {
				if (!deque.contains(insertedNumber))
					previousCount++;
				int polledNumber = deque.pollFirst();
				if (!deque.contains(polledNumber))
					previousCount--;
			}

			deque.add(insertedNumber);
			if (i + 1 == m)
				previousCount = deque.stream().distinct().count();
			if (previousCount > max)
				max = previousCount;
		}

		in.close();
		System.out.println(max);
	}
}
