package implementation.electronics_shop;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

	/*
	 * Complete the getMoneySpent function below.
	 */
	static int getMoneySpent(int[] keyboards, int[] drives, int b) {
		List<Integer> keyboardList = Arrays.stream(keyboards).boxed().filter(x -> x < b).collect(Collectors.toList());
		List<Integer> drivesList = Arrays.stream(drives).boxed().filter(x -> x < b).collect(Collectors.toList());

		Collections.sort(keyboardList);
		Collections.sort(drivesList);

		return getMaxRecord(keyboardList, drivesList, b);
	}

	private static int getMaxRecord(List<Integer> first, List<Integer> second, int b) {
		int max = -1;

		for (int i = 0; i < first.size(); i++) {
			int firstValue = first.get(first.size() - (i + 1));

			for (int j = 0; j < second.size(); j++) {
				int sexondValue = second.get(second.size() - (j + 1));
				int sum = firstValue + sexondValue;

				if (sum <= b && sum > max) {
					max = sum;
				}
			}
		}

		return max;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		String[] bnm = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

		int b = Integer.parseInt(bnm[0]);

		int n = Integer.parseInt(bnm[1]);

		int m = Integer.parseInt(bnm[2]);

		int[] keyboards = new int[n];

		String[] keyboardsItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

		for (int keyboardsItr = 0; keyboardsItr < n; keyboardsItr++) {
			int keyboardsItem = Integer.parseInt(keyboardsItems[keyboardsItr]);
			keyboards[keyboardsItr] = keyboardsItem;
		}

		int[] drives = new int[m];

		String[] drivesItems = scanner.nextLine().split(" ");

		for (int drivesItr = 0; drivesItr < m; drivesItr++) {
			int drivesItem = Integer.parseInt(drivesItems[drivesItr]);
			drives[drivesItr] = drivesItem;
		}

		/*
		 * The maximum amount of money she can spend on a keyboard and USB
		 * drive, or -1 if she can't purchase both items
		 */

		int moneySpent = getMoneySpent(keyboards, drives, b);

		System.out.println(moneySpent);
		scanner.close();
	}
}
