import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        in.nextLine();

        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            for (int j = 0; j < n; j++) {
                graph[i][j] = (line.charAt(j) - '0');
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] == 0) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    graph[i][k] += 10 - graph[j][k];
                    graph[i][k] %= 10;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }
    }
}
