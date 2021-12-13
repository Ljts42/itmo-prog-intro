import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[][] result = new int[100][];
        int n = 0;

        while (in.hasNextLine()) {
            result[n] = new int[100];
            
            Scanner line = new Scanner(in.nextLine());
            int m = 1;

            while (line.hasNextInt()) {
                result[n][m] = line.nextInt();
                m++;
                
                if (m >= result[n].length) {
                    int[] new_row = new int[result[n].length * 2];
                    System.arraycopy(result[n], 0, new_row, 0, result[n].length);
                    result[n] = new_row;
                }
            }
            result[n][0] = m;
            n++;
            
            if (n >= result.length - 1) {
                int[][] new_res = new int[result.length * 2][];
                System.arraycopy(result, 0, new_res, 0, result.length);
                result = new_res;
            }
        }

        for (int i = n - 1; i > -1; i--) {
            for (int j = result[i][0] - 1; j > 0; j--) {
                System.out.print((result[i][j] + ((j == 1) ? "" : " ")));
            }
            System.out.println();
        }
    }
}
