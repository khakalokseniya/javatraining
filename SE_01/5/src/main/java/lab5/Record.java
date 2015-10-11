package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * 
 * contains all records
 *
 */
public class Record {
	static ArrayList<String> records = new ArrayList<String>();
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	/**
	 * add a new string in array of records
	 * @param arg
	 */
	public static void add(String arg) {
		records.add(arg);
	}
    /**
     * delete a string
     * @param a
     * @throws IOException
     */
	public static void delete(int a) throws IOException { 
		System.out.println("Номер удаляемой строки");
		String q = read.readLine();
		a = Integer.parseInt(q);
		records.remove(a-1);
	}
/**
 * changes the value of a string
 * @param n
 * @param b
 * @throws IOException
 */
	public static void change(int n, String b) throws IOException {
		System.out.println("Введите новое содержание");
		String a = read.readLine();
		int index = records.indexOf(b);
		records.set(index, a);
	}
/**
 * print all records
 */
	public static void print() {
		for (int r = 0; r < records.size(); r++) {
			System.out.println(records.get(r));
		}
	}
}
