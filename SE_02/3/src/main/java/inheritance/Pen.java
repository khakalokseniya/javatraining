package inheritance;

public class Pen extends WritingArticles{

	public Pen(String name, int price) {
		super(name, price);
	}
	
	public void info(){
		System.out.print("Pen");
	}

}
