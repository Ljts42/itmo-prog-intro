import java.math.BigInteger;
import java.io.IOException;

public class ReverseHex2 {
    public static void main(String[] args) {
        try {
            MyScanner in = new MyScanner(System.in, new HexIntChecker());

            int[][] result = new int[10][];
            int n = 0;

            in.hasNext();
            int line = in.getLineNumber();

            while (in.hasNext()) {
                int m = 1;
                while (n != line - 1) {
                    result[n] = new int[10];
                    result[n++][0] = m;

                    if (n >= result.length - 1) {
                        int[][] new_res = new int[result.length * 2][];
                        System.arraycopy(result, 0, new_res, 0, result.length);
                        result = new_res;
                    }
                }

                result[n] = new int[10];

                while (in.hasNext()) {
                    if (in.getLineNumber() != line) {
                        line = in.getLineNumber();
                        break;
                    }
                    result[n][m++] = new BigInteger(in.nextWord(), 16).intValue();

                    if (m >= result[n].length) {
                        int[] new_row = new int[result[n].length * 2];
                        System.arraycopy(result[n], 0, new_row, 0, result[n].length);
                        result[n] = new_row;
                    }
                }
                result[n++][0] = m;

                if (n >= result.length - 1) {
                    int[][] new_res = new int[result.length * 2][];
                    System.arraycopy(result, 0, new_res, 0, result.length);
                    result = new_res;
                }
            }
            line = in.getLineNumber();

            while (n < line - 1) {
                result[n] = new int[10];
                result[n++][0] = 1;
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
        } catch (IOException e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }
}
