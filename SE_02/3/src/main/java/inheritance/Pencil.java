package inheritance;

public class Pencil extends WritingArticles{

	public Pencil(String name, int price) {
		super(name, price);
	}
	
	public void info(){
		System.out.print("Pencil");
	}

}

