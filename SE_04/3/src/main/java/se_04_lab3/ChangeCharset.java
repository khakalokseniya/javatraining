package se_04_lab3;

import java.io.*;
import java.nio.charset.Charset;

public class ChangeCharset {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("f:", "Set.java");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), Charset.forName("UTF-8")));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), Charset.forName("UTF-16")));
	}
}
