package stationery;

public class Stationery {
	private String name;
	private int price;
	private static int sum;
	
	public Stationery(String name, int price){
		this.name = name;
		this.price = price;
		sum += price;
	}
	
		
	public int getPrice(){
		return price;
	}
	
	public static int sum(){
		return sum;
	}
	
}
