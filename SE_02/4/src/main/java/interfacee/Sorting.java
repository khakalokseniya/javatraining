package interfacee;

import inheritance.Stationery;

public class Sorting {
	public int compare(Stationery o1, Stationery o2){
		return Integer.compare(o1.getPrice(),o2.getPrice());
				}

}
