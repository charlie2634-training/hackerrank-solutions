package data_structures.java_sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Student {
	private int id;
	private String fname;
	private double cgpa;

	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}

	public int getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public double getCgpa() {
		return cgpa;
	}
}

// Complete the code
public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());

		List<Student> studentList = new ArrayList<Student>();
		while (testCases > 0) {
			int id = in.nextInt();
			String fname = in.next();
			double cgpa = in.nextDouble();

			Student st = new Student(id, fname, cgpa);
			studentList.add(st);

			testCases--;
		}

		studentList.sort(Comparator.comparing(Student::getCgpa).reversed().thenComparing(Student::getFname)
				.thenComparing(Student::getId));

		/*
		 * studentList.sort( Comparator.comparing((Student x) -> x.getCgpa())
		 * .reversed() .thenComparing((Student x) -> x.getFname())
		 * .thenComparing((Student x) -> x.getId()) );
		 */

		for (Student st : studentList) {
			System.out.println(st.getFname());
		}
		
		in.close();
	}
}
