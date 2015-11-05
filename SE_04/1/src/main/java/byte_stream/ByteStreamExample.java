package byte_stream;

import java.io.*;
import java.util.HashMap;

public class ByteStreamExample {

	public static void main(String[] args) {
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		File file = new File("f:", "StarterSet.java");
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			byte[] content = new byte[is.available()];
			is.read(content);
			String[] lines = new String(content).split("\n");

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
		} catch (IOException e) {
			System.err.println("Ошибка файла: " + e);
		}
		File finish = new File("f:\\Lab4_1");
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(new FileOutputStream(finish));
			os.writeObject(hashmap);

		} catch (IOException e) {
			System.err.println("Ошибка файла: " + e);
		}
	}
}