package stationery;

public class Accounting {
	public static void main(String[] args){
		Stationery pen = new Stationery("Pen", 5000);
		Stationery pencil = new Stationery("Pencil", 10000);
		System.out.println(Stationery.sum());
	}
}
