package inheritance;

public class Pencil extends Stationery {

	public Pencil(String name, int price) {
		super(name, price);
	}

	public void info() {
		System.out.println("Pencil");
	}

	@Override
	public String toString() {
		return "Pencil " + getName() + " " + getPrice();
	}

}
