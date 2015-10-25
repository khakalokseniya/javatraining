package se_02_lab5;

import java.util.ArrayList;
import java.util.HashMap;

public class Students {
	private String name;

	public Students(String name) {
		this.name = name;
	}

	HashMap<Discipline, ArrayList<Integer>> map = new HashMap<Discipline, ArrayList<Integer>>();

	public void addMark(Discipline dis, int mark) {
		ArrayList<Integer> arrayOfMarks = map.get(dis);
		if (arrayOfMarks != null) {
			arrayOfMarks.add(mark);
		} else {
			arrayOfMarks = new ArrayList<Integer>();
			arrayOfMarks.add(mark);
			map.put(dis, arrayOfMarks);
		}
		
	}

	public ArrayList<Integer> getMarksForDiscipline(Discipline disp) {
		return map.get(disp);
	}

}
