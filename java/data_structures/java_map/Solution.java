package data_structures.java_map;

//Complete this code or write your own from scratch
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
	public static void main(String[] argh) {
		Map<String, Integer> phoneBook = new HashMap<>();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		for (int i = 0; i < n; i++) {
			String name = in.nextLine();
			int phone = in.nextInt();
			in.nextLine();

			phoneBook.put(name, phone);
		}
		while (in.hasNext()) {
			String name = in.nextLine();
			Integer phone = phoneBook.get(name);

			if (phone != null) {
				System.out.println(name + "=" + phone);
			} else {
				System.out.println("Not found");
			}
		}
		in.close();
	}
}
