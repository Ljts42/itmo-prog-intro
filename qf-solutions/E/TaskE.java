import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class TaskE {
    private static ArrayList<ArrayList<Integer>> graph;
    private static Queue<Integer> queue;
    private static boolean[] visited;
    private static int[] depths;
    private static int[] parent;
    private static int n;

    private static void bfs(int start) {
        depths = new int[n];
        parent = new int[n];
        visited = new boolean[n];
        queue = new LinkedList<Integer>();
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
                    parent[next] = vertex;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            MyScanner in = new MyScanner(System.in, new CharChecker());
            try {
                n = in.nextInt();
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
                if (n == 2) {
                    System.out.println("NO");
                    return;
                }

                bfs(c[0]);
                int maxD = 0;
                int maxV = c[0];
                for (int i = 0; i < m; i++) {
                    if (depths[c[i]] > maxD) {
                        maxD = depths[c[i]];
                        maxV = c[i];
                    }
                }
                if (maxD % 2 != 0) {
                    System.out.println("NO");
                    return;
                }

                while (maxV != c[0]) {
                    if (depths[maxV] == maxD / 2) {
                        break;
                    }
                    maxV = parent[maxV];
                }
                bfs(maxV);
                for (int j = 0; j < m; j++) {
                    if (depths[c[j]] != maxD / 2) {
                        System.out.println("NO");
                        return;
                    }
                }
                System.out.println("YES");
                System.out.println(maxV + 1);
    } catch (IOException e) {
                System.out.println("Input file error: " + e.getMessage());
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input file error: " + e.getMessage());
        }
    }
}

class CharChecker {
    boolean check(char c) {
        return (Character.isDigit(c) || c == '-');
    }
}

class MyScanner {
    private Reader reader;
    private CharChecker checker;

    private char[] buffer;
    private int readLen = 0;
    private int curPos = 0;

    private int lineNumber = 1;
    private char prevLineEndChar = ' ';

    public MyScanner(InputStream in, CharChecker checker) {
        this(new InputStreamReader(in), checker, 1024);
    }

    public MyScanner(InputStream in, CharChecker checker, int len) {
        this(new InputStreamReader(in), checker, len);
    }

    public MyScanner(Reader reader, CharChecker checker) {
        this(reader, checker, 1024);
    }

    public MyScanner(Reader reader, CharChecker checker, int len) {
        this.checker = checker;
        this.reader = reader;
        buffer = new char[len];
    }


    private boolean readBuffer() throws IOException {
        if (curPos >= readLen) {
            readLen = reader.read(buffer);
            curPos = 0;
        }
        return (readLen > 0);
    }

    private boolean isLineEndChar(char c) {
        return ((c == '\036') || (c == '\n') ||
                (c == '\025') || (c == '\r'));
    }

    private void skip() throws IOException {
        while (readBuffer() && !checker.check(buffer[curPos])) {
            if (isLineEndChar(buffer[curPos])) {
                if ((buffer[curPos] == '\n') &&
                    (prevLineEndChar == '\r')) {
                    prevLineEndChar = ' ';
                } else if ((buffer[curPos] == '\r') &&
                    (prevLineEndChar == '\n')) {
                    prevLineEndChar = ' ';
                } else {
                    prevLineEndChar = buffer[curPos];
                    lineNumber++;
                }
            } else {
                prevLineEndChar = ' ';
            }
            curPos++;
        }
    }

    public boolean hasNext() throws IOException {
        skip();
        return readBuffer();
    }

    public String nextWord() throws IOException {
        if (!hasNext()) {
            return "";
        }

        StringBuilder word = new StringBuilder();
        while (readBuffer() && checker.check(buffer[curPos])) {
            word.append(buffer[curPos]);
            curPos++;
        }
        return word.toString();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextWord());
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void close() throws IOException {
        reader.close();
    }
}