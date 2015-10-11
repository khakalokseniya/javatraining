package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("¬ведите размер массива");
		String s = read.readLine();
		int l = Integer.parseInt(s);

		int[][] matrix = new int[l][l];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (i == j) {
					matrix[i][j] = 1;
				} else if (j == l - 1 - i) {
					matrix[i][j] = 1;
				} else
					matrix[i][j] = 0;
			}
		}
		for (int k = 0; k < matrix.length; k++) {
			for (int m = 0; m < matrix.length; m++) {
				System.out.print(matrix[k][m]);
			}
			System.out.println();
		}
	}
}
