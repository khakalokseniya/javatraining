package inheritance;

public class Paper extends Stationery {

	public Paper(String name, int price) {
		super(name, price);
	}

	public void info() {
		System.out.println("Paper");
	}

	@Override
	public String toString() {
		return "Paper " + getName() + " " + getPrice();
	}

}
