package algorithms.implementation.non_divisible_subset;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Result {

	private static class ValueWithModule {
		private int value;
		private int module;

		public ValueWithModule(int value, int module) {
			super();
			this.value = value;
			this.module = module;
		}

		public int getValue() {
			return value;
		}

		public int getModule() {
			return module;
		}

		@Override
		public String toString() {
			return String.format("[%d,%d]", value, module);
		}
	}

	public static int nonDivisibleSubset(int k, List<Integer> s) {
		Map<Integer, Integer> moduleValues = new HashMap<>();
		Map<Integer, Integer> module0Values = new HashMap<>();
		Map<Integer, Integer> moduleK2Values = new HashMap<>();

		List<ValueWithModule> valuesWithModule = s.stream().map(x -> new ValueWithModule(x, x % k))
				.collect(Collectors.toList());

		for (int i = 0; i < valuesWithModule.size(); i++) {
			int value = valuesWithModule.get(i).getValue();
			int module = valuesWithModule.get(i).getModule();

			if (module == 0) {
				if (!module0Values.keySet().contains(value)) {
					module0Values.put(value, 0);
				}
				module0Values.put(value, module0Values.get(value) + 1);
			} else if (module == k / 2 && k % 2 == 0) {
				if (!moduleK2Values.keySet().contains(value)) {
					moduleK2Values.put(value, 0);
				}
				moduleK2Values.put(value, moduleK2Values.get(value) + 1);
			} else {
				if (!moduleValues.keySet().contains(module)) {
					moduleValues.put(module, 0);
				}
				moduleValues.put(module, moduleValues.get(module) + 1);
			}
		}

		System.out.println(valuesWithModule);
		System.out.println(moduleValues);
		System.out.println(module0Values);
		System.out.println(moduleK2Values);

		int maximumModuleValue = 0;
		List<Integer> alreadyVisited = new ArrayList<>();

		for (Integer index : moduleValues.keySet()) {
			if (alreadyVisited.contains(index))
				continue;
			alreadyVisited.add(index);
			alreadyVisited.add(k - index);

			int currentNumberOfElements = moduleValues.get(index) != null ? moduleValues.get(index) : 0;
			int currentNumberOfElementsOfComplement = moduleValues.get(k - index) != null ? moduleValues.get(k - index)
					: 0;

			maximumModuleValue += Math.max(currentNumberOfElements, currentNumberOfElementsOfComplement);
		}

		int maximumModule0Value = module0Values.size() > 0 ? 1 : 0;
		int maximumModuleK2Value = moduleK2Values.size() > 0 ? 1 : 0;

		return maximumModuleValue + maximumModule0Value + maximumModuleK2Value;
	}
}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(toList());

		int result = Result.nonDivisibleSubset(k, s);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
