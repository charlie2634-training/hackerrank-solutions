package java.data_structures.java_1d_array_part_2;

import java.util.Scanner;

public class Solution {

	public static boolean canWin(int leap, int[] game) {
		if (leap >= game.length)
			return true;
		if (leap == 0) {
			for (int value : game) {
				if (value == 1)
					return false;
			}
			return true;
		}

		return currentSegmentCanWin(0, leap, game);
	}

	private static boolean currentSegmentCanWin(int index, int leap, int[] game) {

		while (true) {
			if (index < game.length && game[index] == 0) {
				if (canWinAddingLeap(index, leap, game))
					return true;
				else
					index++;
			} else if (index >= game.length) {
				return true;
			} else {
				break;
			}
		}

		return false;
	}

	private static boolean canWinAddingLeap(int index, int leap, int[] game) {
		int newIndex = index + leap;

		if (newIndex >= game.length)
			return true;

		if (newIndex < game.length && game[newIndex] == 0) {
			while (true) {
				if (newIndex > 0 && game[newIndex - 1] == 0 && newIndex > (index + 1)) {
					newIndex--;
				} else
					break;
			}

			return currentSegmentCanWin(newIndex, leap, game);
		}

		return false;
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int q = scan.nextInt();
		while (q-- > 0) {
			int n = scan.nextInt();
			int leap = scan.nextInt();

			int[] game = new int[n];
			for (int i = 0; i < n; i++) {
				game[i] = scan.nextInt();
			}

			System.out.println((canWin(leap, game)) ? "YES" : "NO");
		}
		scan.close();
	}
}
