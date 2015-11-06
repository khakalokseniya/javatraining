package serialization;

import java.io.*;

public class Serializator {
	public boolean serialization(Film film, String filename) {
		boolean flag = false;
		File file = new File(filename);
		ObjectOutputStream outstream = null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			if (fos != null) {
				outstream = new ObjectOutputStream(fos);
				outstream.writeObject(film);
				flag = true;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Файл не может быть создан, потому что: " + e);
		} catch (NotSerializableException e) {
			System.err.println("Класс не поддерживает сериализацию " + e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				outstream.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		return flag;
	}

	public Film deserialization(String fileName) throws InvalidObjectException,
			ClassNotFoundException {
		File dfile = new File(fileName);
		ObjectInputStream instream = null;
		try {
			FileInputStream fis = new FileInputStream(dfile);
			instream = new ObjectInputStream(fis);
			Film fl = (Film) instream.readObject();
			return fl;
		} catch (IOException ce) {
			System.err.println(ce);
		} catch (ClassNotFoundException ne) {
			System.err.println(ne);
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		throw new InvalidObjectException("объект не восстановлен");
	}
}
