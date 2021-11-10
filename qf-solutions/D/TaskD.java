import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class TaskD {
    public static void main(String[] args) {
        try {
            MyScanner in = new MyScanner(System.in, new CharChecker());
            try {
                long modulo = 998244353;
                int n = in.nextInt(), k = in.nextInt();

                long[] p = new long[n + 1];
                p[0] = 1;
                for (int i = 1; i <= n; i++) {
                    p[i] = (p[i - 1] * k) % modulo;
                }

                long[] r = new long[n + 1];
                for (int i = 1; i <= n; i++) {
                    if (i % 2 == 0) {
                        /*
                        i = 2 * m
                        R(i) = k^0 * k^m + k^1 * k^m + k^1 * k^(m-1) + ... + k^(m-1) * k^1 + k^m * k^1
                        */
                        r[i] = ((i / 2) * (p[i / 2] + p[i / 2 + 1])) % modulo;
                    } else {
                        /*
                        i = 2 * m + 1
                        R(i) = k^0 * k^(m+1) + k^1 * k^m + ... + k^m * k^1
                        */
                        r[i] = (i * p[(i + 1) / 2]) % modulo;
                    }
                }

                long[] d = new long[n + 1];
                d[1] = k;
                for (int i = 2; i <= n; i++) {
                    d[i] = r[i] % modulo;
                    for (int j = 1; j * j <= i; j++) {
                        if (i % j == 0) {
                            d[i] -= (i / j) * d[j];
                            if (j * j != i && j != 1) {
                                d[i] -= j * d[i / j];
                            }
                            d[i] %= modulo;
                            if (d[i] < 0) {
                                d[i] += modulo;
                            }
                        }
                    }
                    d[i] %= modulo;
                    if (d[i] < 0) {
                        d[i] += modulo;
                    }
                }

                long result = 0;
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j * j <= i; j++) {
                        if (i % j == 0) {
                            result += d[j];
                            if (j * j != i) {
                                result += d[i / j];
                            }
                            result %= modulo;
                        }
                    }
                }
                System.out.println(result);
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