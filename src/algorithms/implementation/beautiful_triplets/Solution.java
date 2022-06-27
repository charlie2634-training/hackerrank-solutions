package algorithms.implementation.beautiful_triplets;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

class Result {

	static class TripletFormula {
		private int numberOfFirstElements;
		private int numberOfSecondElements;
		private int numberOfThirdElements;

		public TripletFormula() {
			super();
		}

		public void addFirstElement() {
			numberOfFirstElements++;
		}

		public void addSecondElement() {
			numberOfSecondElements++;
		}

		public void addThirdElement() {
			numberOfThirdElements++;
		}

		public int getNumberOfFirstElements() {
			return numberOfFirstElements;
		}

		public int getNumberOfSecondElements() {
			return numberOfSecondElements;
		}

		public int getNumberOfThirdElements() {
			return numberOfThirdElements;
		}
	}

	public static int beautifulTriplets(int d, List<Integer> arr) {
		Map<Integer, TripletFormula> map = new HashMap<>();

		// This has a complexity of O(N).
		for (int i = 0; i < arr.size(); i++) {
			Integer currentValue = arr.get(i);
			Integer previousTriplet = currentValue - d;
			Integer evenPreviousValue = previousTriplet - d;

			map.putIfAbsent(currentValue, new TripletFormula());
			map.putIfAbsent(previousTriplet, new TripletFormula());
			map.putIfAbsent(evenPreviousValue, new TripletFormula());

			map.get(currentValue).addFirstElement();
			map.get(previousTriplet).addSecondElement();
			map.get(evenPreviousValue).addThirdElement();
		}

		Integer count = 0;

		// This has a complexity of: Better case O(1), worst case O(N).
		for (Entry<Integer, TripletFormula> entry : map.entrySet()) {
			TripletFormula tripletFormula = entry.getValue();

			count += tripletFormula.getNumberOfFirstElements() * tripletFormula.getNumberOfSecondElements()
					* tripletFormula.getNumberOfThirdElements();
		}

		return count;
	}

}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int d = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = Result.beautifulTriplets(d, arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
