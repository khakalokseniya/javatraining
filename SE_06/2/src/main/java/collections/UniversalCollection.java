package collections;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UniversalCollection {
	public static void main(String[] args) {
		FileInputStream fis;
		Properties property = new Properties();
		try {
			fis = new FileInputStream("src/main/resources/summer.properties");
			property.load(fis);
			Map<String, String> map = new HashMap<String, String>();
			for (String key : property.stringPropertyNames()) {
				map.put(key, property.getProperty(key));
			}
			System.out.println(map);
		} catch (IOException e) {
			System.err.println("Файл свойств отсутствует " + e);
		}
	}
}
//если добавить в properties элемент, который уже есть, в map заменится старый и отобразится на его месте новый