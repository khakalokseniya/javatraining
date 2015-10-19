package inheritance;

import java.util.ArrayList;
import java.util.Arrays;

public class StarterSet {

	public static void main(String[] args) {
				
		Stationery pen = new Pen("Parker", 50000);
		pen.info();
		
		Stationery folder = new Folder("Small folder", 5000);
		Stationery pencil = new Pencil("Pencil", 10000);
		Stationery paper = new Paper("Paper", 15000);
	
		ArrayList<Object> starter_set = new ArrayList<Object>(Arrays.asList(pen, folder, pencil, paper));
		
		}
		

	}

