import java.util.Scanner;
public class D {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long modulo = 998244353;
        int n = in.nextInt(), k = in.nextInt();

        long[] p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = (p[i - 1] * k) % modulo;
        }

        long[] r = new long[n + 1];
        for (long i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                /*
                i = 2 * m
                R(i) = k^0 * k^m + k^1 * k^m + k^1 * k^(m-1) + ... + k^(m-1) * k^1 + k^m * k^1
                */
                r[(int) i] = ((i / 2) * (p[(int) i / 2] + p[(int) i / 2 + 1])) % modulo;
            } else {
                /*
                i = 2 * m + 1
                R(i) = k^0 * k^(m+1) + k^1 * k^m + ... + k^m * k^1
                */
                r[(int) i] = (i * p[(int) (i + 1) / 2]) % modulo;
            }
        }

        long[] d = new long[n + 1];
        d[1] = k;
        for (long i = 2; i <= n; i++) {
            d[(int) i] = r[(int) i] % modulo;
            for (long j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    d[(int) i] -= (i / j) * d[(int) j];
                    if (j * j != i && j != 1) {
                        d[(int) i] -= j * d[(int) (i / j)];
                    }
                    d[(int) i] %= modulo;
                    if (d[(int) i] < 0) {
                        d[(int) i] += modulo;
                    }
                }
            }
            d[(int) i] %= modulo;
            if (d[(int) i] < 0) {
                d[(int) i] += modulo;
            }
        }

        long result = 0;
        for (long i = 1; i <= n; i++) {
            for (long j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    result += d[(int) j];
                    if (j * j != i) {
                        result += d[(int) (i / j)];
                    }
                    result %= modulo;
                }
            }
        }
        System.out.println(result);
    }
}
