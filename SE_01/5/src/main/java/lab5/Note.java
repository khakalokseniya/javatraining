package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Note {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

		String record = read.readLine();
		Record.add(record);
		
		String record2 = read.readLine();
		Record.add(record2);

		int a = 0;
		Record.change(a, record);

		Record.print();
		
		int b = 0;
		Record.delete(b);
		
		Record.print();

	}

}
