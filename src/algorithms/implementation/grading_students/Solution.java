package algorithms.implementation.grading_students;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Result {

	/*
	 * Complete the 'gradingStudents' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY. The function accepts
	 * INTEGER_ARRAY grades as parameter.
	 */

	public static List<Integer> gradingStudents(List<Integer> grades) {

		List<Integer> result = new ArrayList<>();

		for (Integer grade : grades) {
			System.out.printf("%d grade 5 = %d\n", grade, grade % 5);

			if (grade >= 38 && grade % 5 > 2) {
				grade += (5 - grade % 5);
			}

			result.add(grade);
		}

		return result;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int gradesCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> grades = new ArrayList<>();

		for (int i = 0; i < gradesCount; i++) {
			int gradesItem = Integer.parseInt(bufferedReader.readLine().trim());
			grades.add(gradesItem);
		}

		List<Integer> result = Result.gradingStudents(grades);

		for (int i = 0; i < result.size(); i++) {
			bufferedWriter.write(String.valueOf(result.get(i)));

			if (i != result.size() - 1) {
				bufferedWriter.write("\n");
			}
		}

		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
