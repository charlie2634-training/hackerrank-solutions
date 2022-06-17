package data_structures.java_priority_queue;

import java.util.ArrayList;
import java.util.List;

class Student implements Comparable<Object> {
	private int id;
	private String name;
	private double cgpa;

	public Student(int id, String name, double cgpa) {
		this.id = id;
		this.name = name;
		this.cgpa = cgpa;
	}

	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public double getCGPA() {
		return this.cgpa;
	}

	@Override
	public int compareTo(Object object) {
		if (!(object instanceof Student))
			return 0;

		Student target = (Student) object;

		if (this.getCGPA() == target.getCGPA()) {
			if (this.getName().equals(target.getName())) {
				return new Integer(target.getID()).compareTo(new Integer(this.getID()));
			} else {
				return this.getName().compareTo(target.getName());
			}
		} else {
			return new Double(target.getCGPA()).compareTo(new Double(this.getCGPA()));
		}
	}

	@Override
	public String toString() {
		return String.format("%d %s %f", this.getID(), this.getName(), this.getCGPA());
	}
}

class Priorities {
	public List<Student> getStudents(List<String> events) {
		java.util.Queue<Student> students = new java.util.PriorityQueue<Student>();

		for (String event : events) {
			String[] entries = event.split(" ");
			String action = entries[0];

			if (action.equals("ENTER")) {
				String name = entries[1];
				double cgpa = Double.valueOf(entries[2]);
				int id = Integer.valueOf(entries[3]);

				Student student = new Student(id, name, cgpa);
				students.add(student);
			} else if (action.equals("SERVED")) {
				students.poll();
			}
		}

		List<Student> returnable = new ArrayList<>();
		while (students.size() > 0)
			returnable.add(students.poll());
		return returnable;
	}
}
