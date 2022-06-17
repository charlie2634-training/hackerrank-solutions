package implementation.taum_and_b_day;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.stream.IntStream;

class Result {

	public static long taumBday(int b, int w, int bc, int wc, int z) {
		BigInteger blackCostsFromBlack = BigInteger.valueOf(b).multiply(BigInteger.valueOf(bc));
		BigInteger blackCostsFromWhite = BigInteger.valueOf(b).multiply(BigInteger.valueOf(wc + z));
		BigInteger whiteCostsFromBlack = BigInteger.valueOf(w).multiply(BigInteger.valueOf(bc + z));
		BigInteger whiteCostsFromWhite = BigInteger.valueOf(w).multiply(BigInteger.valueOf(wc));

		BigInteger allFromBlack = blackCostsFromBlack.add(whiteCostsFromBlack);
		BigInteger allFromWhite = blackCostsFromWhite.add(whiteCostsFromWhite);
		BigInteger allFromEach = blackCostsFromBlack.add(whiteCostsFromWhite);

		return allFromBlack.min(allFromWhite).min(allFromEach).longValue();
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
				String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				int b = Integer.parseInt(firstMultipleInput[0]);

				int w = Integer.parseInt(firstMultipleInput[1]);

				String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

				int bc = Integer.parseInt(secondMultipleInput[0]);

				int wc = Integer.parseInt(secondMultipleInput[1]);

				int z = Integer.parseInt(secondMultipleInput[2]);

				long result = Result.taumBday(b, w, bc, wc, z);

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
