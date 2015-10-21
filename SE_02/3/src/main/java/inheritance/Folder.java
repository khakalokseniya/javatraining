package inheritance;

public class Folder extends Stationery {
	public Folder(String name, int price) {
		super(name, price);
	}

	public void info() {
		System.out.println("Folder");
	}

	@Override
	public String toString() {
		return "Folder " + getName() + " " + getPrice();
	}

}
