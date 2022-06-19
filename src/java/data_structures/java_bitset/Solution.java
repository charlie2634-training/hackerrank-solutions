package java.data_structures.java_bitset;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numberOfElements = scanner.nextInt();
		int numberOfQueries = scanner.nextInt();

		List<BitSet> bitSets = new ArrayList<>();
		bitSets.add(new BitSet(numberOfElements));
		bitSets.add(new BitSet(numberOfElements));

		for (int i = 0; i < numberOfQueries; i++) {

			String action = scanner.next();
			int bitSetIndicator = scanner.nextInt() - 1;
			int bitSetIndex = scanner.nextInt();

			switch (action) {
			case "AND":
				bitSets.get(bitSetIndicator).and(bitSets.get(1 - bitSetIndicator));
				break;
			case "OR":
				bitSets.get(bitSetIndicator).or(bitSets.get(1 - bitSetIndicator));
				break;
			case "XOR":
				bitSets.get(bitSetIndicator).xor(bitSets.get(1 - bitSetIndicator));
				break;
			case "FLIP":
				bitSets.get(bitSetIndicator).set(bitSetIndex, !bitSets.get(bitSetIndicator).get(bitSetIndex));
				break;
			case "SET":
				bitSets.get(bitSetIndicator).set(bitSetIndex, true);
				break;
			}

			System.out.printf("%d %d\n", bitSets.get(0).cardinality(), bitSets.get(1).cardinality());
		}

		scanner.close();
	}
}
