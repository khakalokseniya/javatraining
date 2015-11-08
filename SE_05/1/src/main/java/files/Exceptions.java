package files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Exceptions {
	public static void main(String[] args) {
		// просмотр директории и отображение сожержимого
		File file = new File("e:\\Английский");
		if (file.isDirectory()) {
			for (File item : file.listFiles()) {
				if (item.isDirectory()) {
					System.out.println(item.getName() + " \tкаталог");
				} else {
					System.out.println(item.getName() + "\tфайл");
				}
			}
		} else {
			System.out.println(file.getName() + "\tфайл");
		}

		// создание нового файла
		String path = "e:\\Английский\\file.txt";
		File newFile = new File(path);
		try {
			if (newFile.createNewFile()) {
				System.out.println("файл создан");
			} else
				System.out.println("Файл уже существует");
		} catch (IOException e) {
			System.err.println(e);
		}

		// запись в файл
		PrintWriter writer;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(newFile,true))); // true дозаписывает в конец
			writer.write("Без труда не выловишь и рыбку из пруда");
			writer.flush();
			System.out.println("Текст записался");
		} catch (IOException e) {
			;
			System.err.println(e);
		}

		// удаление файла
		newFile.delete();
		System.out.println("Файл удален");

	}
}
