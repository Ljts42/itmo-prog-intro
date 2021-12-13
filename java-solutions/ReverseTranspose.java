import java.util.Scanner;

public class ReverseTranspose {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int[][] result = new int[100][100];
		int n = 1;

		while (in.hasNextLine()) {
			Scanner line = new Scanner(in.nextLine());
			int m = 1;

			while (line.hasNextInt()) {
				result[n][m] = line.nextInt();
				result[0][m++]++;

				if (m == result[n].length) {
					int[] newRow = new int[result[n].length * 2];
					System.arraycopy(result[n], 0, newRow, 0, result[n].length);
					result[n] = newRow;
				}

				if (m == result[0].length) {
					int[] newRow = new int[result[0].length * 2];
					System.arraycopy(result[0], 0, newRow, 0, result[0].length);
					result[0] = newRow;
				}
			}

			if (m != 1) {
				result[0][0] = (result[0][0] < m) ? m : result[0][0];
				result[n++][0] = m;
				
				if (n >= result.length) {
					int[][] newRes = new int[result.length * 2][100];
					System.arraycopy(result, 0, newRes, 0, result.length);
					result = newRes;
				}
			}
		}

		for (int i = 1; i < result[0][0]; i++) {
			int k = 0;
			for (int j = 1; j <= result[0][i]; j++) {
				while (result[j + k][0] <= i) {
					k++;
				}
				System.out.print(result[j + k][i] + ((j == result[0][i]) ? "\n" : " "));
			}
		}
	}
}