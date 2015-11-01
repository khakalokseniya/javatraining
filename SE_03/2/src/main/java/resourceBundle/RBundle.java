package resourceBundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class RBundle {
	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		Locale enLocale = new Locale("en", "US");
		ResourceBundle enBundle = ResourceBundle.getBundle("questions_en",
				enLocale);
		ResourceBundle ansEnBundle = ResourceBundle.getBundle("answers_en",
				enLocale);

		Locale ruLocale = new Locale("ru", "RU");
		ResourceBundle ruBundle = ResourceBundle.getBundle("questions_ru",
				ruLocale);
		ResourceBundle ansRuBundle = ResourceBundle.getBundle("answers_ru",
				ruLocale);

		System.out
				.println("Выберите язык для отображения: 1 - русский, 2 - английский");
		String s = reader.readLine();
		int lang = Integer.parseInt(s);

		if (lang == 1) {
			Enumeration ruBundleValue = ruBundle.getKeys();
			while (ruBundleValue.hasMoreElements()) {
				String key = (String) ruBundleValue.nextElement();
				String value = ruBundle.getString(key);
				System.out.println(key + " " + value);
			}
			System.out.println("Введите номер вопроса для получения ответа");
			String st = reader.readLine();
			String value = ansRuBundle.getString(st);
			System.out.println(value);

		} else {
			Enumeration enBundleValue = enBundle.getKeys();
			while (enBundleValue.hasMoreElements()) {
				String key = (String) enBundleValue.nextElement();
				String value = enBundle.getString(key);
				System.out.println(key + " " + value);
			}
			System.out.println("Choose number of question");
			String st = reader.readLine();
			String value =ansEnBundle.getString(st);
			System.out.println(value);
		}
	}
}
