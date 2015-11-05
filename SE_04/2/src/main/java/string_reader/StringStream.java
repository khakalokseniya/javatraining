package string_reader;

import java.io.*;
import java.util.HashMap;

public class StringStream {

	public static void main(String[] args) {
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		File file = new File("f:", "StarterSet.java");
		BufferedReader is = null;
		try {
			is = new BufferedReader(new FileReader(file));
			String str;
			while ((str = is.readLine()) != null) {
				String[] lines = new String(str).split("\n");
				for (int i = 0; i < lines.length; i++) {
					String[] words = lines[i].split(" ");
					String keywords = ("byte short int long char float double boolean if else switch case default while do break continue for try catch finally throw throws private protected public import package class interface extends implements static final void abstract native new return this super synchronized volatile const goto instanceof enum assert transient");
					String[] keys = keywords.split(" ");
					for (String word : words) {
						for (int p = 0; p < keys.length; p++) {
							if (word.equals(keys[p])) {
								if (!hashmap.containsKey(word))
									hashmap.put(word, 1);
								else {
									Integer cot = hashmap.get(word);
									cot++;
									hashmap.put(word, cot);
								}
							}
						}
					}
				}
				System.out.println(hashmap);
			}
		} catch (IOException e) {
			System.err.println("Ошибка файла: " + e);
		}
		File finish = new File("f:\\Lab4_2");
		PrintWriter os = null;
		try {
			os = new PrintWriter(new BufferedWriter(new FileWriter(finish)));
			os.print(hashmap);

		} catch (IOException e) {
			System.err.println("Ошибка файла: " + e);
		}
	}
}
