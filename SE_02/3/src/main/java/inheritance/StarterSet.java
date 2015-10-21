package inheritance;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import java.util.ArrayList;

public class StarterSet {

	public static void main(String[] args) {

		Stationery pen = new Pen("Nixon", 50000);
		pen.info();

		Stationery folder = new Folder("Small folder", 5000);
		Stationery pencil = new Pencil("Mini Pencil", 10000);
		Stationery paper = new Paper("Papera", 15000);
		Stationery paper2 = new Paper("Paper", 20000);
		Stationery paper3 = new Paper("Paper", 3000);

		ArrayList<Stationery> starter_set = new ArrayList<Stationery>(
				Arrays.asList(pen, folder, pencil, paper, paper2, paper3));

		Collections.sort(starter_set, new Comparator<Stationery>() {
			public int compare(Stationery o1, Stationery o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});

		for (int i = 0; i < starter_set.size(); i++) {
			System.out.println(starter_set.get(i));
		}

		System.out.println();

		Collections.sort(starter_set, new Comparator<Stationery>() {
			public int compare(Stationery o1, Stationery o2) {
				return Integer.compare(o1.getPrice(), o2.getPrice());
			}
		});
		for (int i = 0; i < starter_set.size(); i++) {
			System.out.println(starter_set.get(i));
		}
		System.out.println();

		Collections.sort(starter_set, new Comparator<Stationery>() {

			public int compare(Stationery o1, Stationery o2) {

				String x1 = ((Stationery) o1).getName();
				String x2 = ((Stationery) o2).getName();
				int sComp = x1.compareTo(x2);

				if (sComp != 0) {
					return sComp;
				} else {
					Integer i1 = ((Stationery) o1).getPrice();
					Integer i2 = ((Stationery) o2).getPrice();
					return i1.compareTo(i2);
				}
			}
		});

		for (int i = 0; i < starter_set.size(); i++) {
			System.out.println(starter_set.get(i));
		}
	}
}
