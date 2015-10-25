package se_02_lab5;

public enum Discipline {
	MATHEMATICS(0), PHYSICS(1), BIOLOGY(2);
	private final int value;

	private Discipline(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
		
}
