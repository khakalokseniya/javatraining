package one;

import java.util.ArrayList;


public class Cycle1 {

	public static void main(String[] args)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < 100; i++) 
		{
			if (i % 2 == 0)
				list.add(i);
		}
		System.out.println(list);
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		for (int q = 1; q < list.size(); q++)
		{
			while (q < list.size()) 
			{				
				list2.add(list.get(q) * list.get(q - 1));
				q += 2;
			}			
		}
	
		System.out.println(list2);
	}
}
