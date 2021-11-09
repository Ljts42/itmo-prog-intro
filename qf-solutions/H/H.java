import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n + 1];
        int maxA = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
            maxA = Math.max(maxA, a[i]);
            a[i] += a[i - 1];
        }

        int[] previous = new int[a[n]];
        int[] results = new int[a[n]];
        for (int i = 1; i <= n; i++) {
            for (int j = a[i - 1]; j < a[i]; j++) {
                previous[j] = a[i - 1];
                results[j] = -1;
            }
        }

        int q = in.nextInt();
        for (int z = 0; z < q; z++) {
            int t = in.nextInt();
            if (t < maxA) {
                System.out.println("Impossible");
                continue;
            }
            if (results[t - 1] == -1) {
                int cnt = t;
                results[t - 1]++;
                while (cnt < a[n]) {
                    cnt = previous[cnt];
                    cnt += t;
                    results[t - 1]++;
                }
                results[t - 1]++;
            }
            System.out.println(results[t - 1]);
        }

    }
}
