package se_02_lab5;

import java.util.ArrayList;

public class Groups {
	private int number;
	private Object Discipline;
	private ArrayList<Students> listOfStudents = new ArrayList<Students>();

	public Groups(int number, Object Discipline) {
		this.number = number;
		this.Discipline = Discipline;
	}

	public void addStudent(Students student) {
		listOfStudents.add(student);
	}

}
