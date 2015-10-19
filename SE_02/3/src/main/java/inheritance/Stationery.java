package inheritance;

public class Stationery {
	private String name;
	private int price;
	
	public Stationery(String name, int price){
		this.name = name;
		this.price = price;
	}
	
	public Stationery(){}

	public int getPrice(){
		return price;
	}
	
	public void info(){
		System.out.println("Stationery");
	}

}
