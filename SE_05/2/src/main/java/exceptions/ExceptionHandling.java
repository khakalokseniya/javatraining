package exceptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExceptionHandling {
	public static void main(String[] args) {
		FileInputStream fis;
		Properties property = new Properties();
		try {
			fis = new FileInputStream("src/main/resources/summer.properties");
			property.load(fis);
			try {
				String[] str = new String[property.size()];
				str[0] = property.getProperty("1");
				str[1] = property.getProperty("2");
				str[2] = property.getProperty("3");
				for (int i = 0; i < str.length; i++) {
					if (str[i] == null) {
						throw new KeyNotFoundException();
					}
				}
			} catch (KeyNotFoundException e) {
				System.err.println("Ключ не найден " + e);
			}
		} catch (IOException e) {
			System.err.println("Файл свойств отсутствует " + e);
		}
	}
}
