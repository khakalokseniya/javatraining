package stringBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogIn {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		CrazyLogger login = new CrazyLogger();

		login.addLog(reader.readLine());
		login.addLog(reader.readLine());
		System.out.println(login.getLog());
		
		System.out.println("¬ведите выражение дл€ поиска");
		String s = reader.readLine();

		System.out.println(login.findLog(s));
	}
}
