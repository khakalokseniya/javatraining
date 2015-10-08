package three;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.ArrayList;

public class function {

	public static void main(String[] args) throws Exception 
	{
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("¬ведите параметры a,b и h");
		String q = read.readLine();
		double a = Double.parseDouble(q);
		String w = read.readLine();
		double b = Double.parseDouble(w);
		String e = read.readLine();
		double h = Double.parseDouble(e);

		ArrayList<Double> list = new ArrayList<Double>();
		ArrayList<Double> list2 = new ArrayList<Double>();
		for (double i = a; i <= b; i += h) 
		{
			list.add(Math.tan(2 * i) - 3);
			list2.add(i);
		}
		for (int s = 0; s < list.size(); s++)
		System.out.println(list2.get(s) + "  |  " + list.get(s));
	}

}
