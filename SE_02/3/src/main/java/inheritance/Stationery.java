package inheritance;

public class Stationery implements Comparable<Object> {
	private String name;
	private int price;

	public Stationery(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public Stationery() {
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void info() {
		System.out.println("Stationery");
	}

	public int compareTo(Object obj) {
		Stationery entry = (Stationery) obj;

		int result = name.compareTo(entry.name);
		if (result != 0) {
			return result;
		}

		result = price - entry.price;
		if (result != 0) {
			return (int) result / Math.abs(result);
		}
		return 0;
	}

}
