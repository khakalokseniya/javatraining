package se_02_lab5;

public class Method {
	public static void main(String[] args) {

		Students one = new Students("Ivanov");
		Students two = new Students("Petrov");
		Students three = new Students("Sidorov");

		Groups group = new Groups(1, Discipline.BIOLOGY);
		one.addMark(Discipline.BIOLOGY, 5);
		two.addMark(Discipline.BIOLOGY, 4);
		three.addMark(Discipline.BIOLOGY, 2);

	}
}
