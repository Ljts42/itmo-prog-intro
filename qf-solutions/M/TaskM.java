import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;


public class TaskM {
    public static void main(String[] args) {
        try {
            MyScanner in = new MyScanner(System.in, new CharChecker());
            try {
                int t = in.nextInt();
                while (t-- != 0) {
                    int n = in.nextInt();
                    int[] a = new int[n];
                    for (int i = 0; i < n; i++) {
                        a[i] = in.nextInt();
                    }
                    
                    int result = 0;
                    Map<Integer, Integer> count = new HashMap<>();
                    for (int j = n - 1; j > 0; j--) {
                        for (int i = 0; i < j; i++) {
                            if (count.containsKey(2 * a[j] - a[i])) {
                                result += count.get(2 * a[j] - a[i]);
                            }
                        }
                        if (count.containsKey(a[j])) {
                            count.put(a[j], count.get(a[j]) + 1);
                        } else {
                            count.put(a[j], 1);
                        }
                    }
                    System.out.println(result);
                }
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
        return Character.isDigit(c);
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