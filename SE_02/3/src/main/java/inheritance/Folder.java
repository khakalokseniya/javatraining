package inheritance;

public class Folder extends Stationery{
	public Folder(String name, int price){
		super(name, price);
	}
	
	public void info(){
		System.out.print("Folder");
	}

}
