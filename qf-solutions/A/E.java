// import java.util.Scanner;
import java.util.*;

public class E {
    // private static int[][] graph;
    private static ArrayList<ArrayList<Integer>> graph;
    private static Queue<Integer> queue;
    private static boolean[] visited;
    private static int[] depths;

    // private static void bfs(int start) {
    //     visited[start] = true;

    // }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n - 1; i++) {
            int v = in.nextInt() - 1, u = in.nextInt() - 1;
            graph.get(v).add(u);
            graph.get(u).add(v);
        }

        int[] c = new int[m];
        for (int i = 0; i < m; i++) {
            c[i] = in.nextInt() - 1;
        }
        if (m == 1) {
            System.out.println("YES");
            System.out.println(c[0] + 1);
            return;
        }

        depths = new int[n];
        visited = new boolean[n];
        queue = new LinkedList<Integer>();
        int start = c[0];
        queue.add(start);
        visited[start] = true;
        depths[start] = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int i = 0; i < graph.get(vertex).size(); i++) {
                int next = graph.get(vertex).get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    depths[next] = depths[vertex] + 1;
                }
            }
        }
        // for (int i : depths) {
        //     System.out.println(i);
        // }
        int maxD = 0;
        for (int i = 0; i < m; i++) {
            maxD = Math.max(depths[c[i]], maxD);
        }
        if (maxD % 2 != 0) {
            System.out.println("NO");
            return;
        }
        if (n == 2) {
            System.out.println("YES");
            System.out.println(c[0] + 1);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (depths[i] == maxD / 2) { // && graph.get(i).size() > 1) {
                start = i;
                break;
            }
        }
        depths = new int[n];
        visited = new boolean[n];
        queue.add(start);
        visited[start] = true;
        depths[start] = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int i = 0; i < graph.get(vertex).size(); i++) {
                int next = graph.get(vertex).get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    depths[next] = depths[vertex] + 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (depths[c[i]] != maxD / 2) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        System.out.println(start + 1);
    }
}
