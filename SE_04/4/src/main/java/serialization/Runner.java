package serialization;

import java.io.InvalidObjectException;

public class Runner {

	public static void main(String[] args) {
		Film gladiator = new Film("Гладиатор", "Рассел Кроу");
		Film greenmile = new Film("Зеленая миля", "Том Хэнкс");
		Film inception = new Film("Начало", "Леонардо ДиКаприо");

		String file = "f:\\films.data";
		Serializator sz = new Serializator();
		boolean b = sz.serialization(gladiator, file);

		Film glad = null;
		try {
			glad = sz.deserialization(file);
		} catch (InvalidObjectException e) {
			e.printStackTrace();
		}
		System.out.println(glad.toString());
	}
}
