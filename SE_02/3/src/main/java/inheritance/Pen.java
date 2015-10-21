package inheritance;

public class Pen extends Stationery {

	public Pen(String name, int price) {
		super(name, price);
	}

	public void info() {
		System.out.println("Pen");
	}

	@Override
	public String toString() {
		return "Pen " + getName() + " " + getPrice();
	}

}
