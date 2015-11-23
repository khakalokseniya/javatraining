package serialization;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class CreateListOfFilms {
	public void filmCreator(Object films) {
		File file = new File("f:\\Films.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pw.print(films);
			pw.flush();
		} catch (IOException e) {
			System.err.println(e);
		}finally{
			pw.close();
		}
	}
}
