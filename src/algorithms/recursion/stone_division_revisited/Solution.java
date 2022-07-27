package algorithms.recursion.stone_division_revisited;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {
	private static List<ProjectedLong> values;

	public static long stoneDivision(long n, List<ProjectedLong> s) {
		values = s;
		return getBestPathValue(new ProjectedLong(n, 1L), 0);
	}

	public static long getBestPathValue(ProjectedLong parent, int index) {
		if (parent.getBestPathValue() == null) {
			long bestPathValue = 0;

			for (int i = index; i < values.size(); i++) {
				ProjectedLong child = values.get(i);

				if (child.getValue() < parent.getValue() && parent.getValue() % child.getValue() == 0) {
					long newBestChildPathValue = parent.getProjection() + getBestPathValue(child, i + 1);

					if (newBestChildPathValue > bestPathValue) {
						bestPathValue = newBestChildPathValue;
					}
				}
			}
			parent.setBestPathValue(bestPathValue);
		}

		return parent.getBestPathValue();
	}
}

class ProjectedLong {
	Long value;
	Long projection;
	Long bestPathValue;

	public ProjectedLong(Long value, Long projection) {
		super();
		this.value = value;
		this.projection = projection;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getProjection() {
		return projection;
	}

	public void setProjection(Long projection) {
		this.projection = projection;
	}

	public Long getBestPathValue() {
		return bestPathValue;
	}

	public void setBestPathValue(Long bestPathValue) {
		this.bestPathValue = bestPathValue;
	}

	public int compareTo(ProjectedLong object) {
		return this.value.compareTo(object.value);
	}

	@Override
	public String toString() {
		return String.format("[value = %d projected = %d]", value, projection);
	}
}

public class Solution {
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				long n = Long.parseLong(firstMultipleInput[0]);

				int m = Integer.parseInt(firstMultipleInput[1]);

				List<ProjectedLong> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(x -> Long.valueOf(x)).filter(x -> n % x == 0).map(x -> new ProjectedLong(x, n / x))
						.collect(toList());

				Collections.sort(s, (x, y) -> y.compareTo(x));

				long result = Result.stoneDivision(n, s);

				bufferedWriter.write(String.valueOf(result));
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
