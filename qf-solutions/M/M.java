import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class M {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while (0 != t--) {
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
    }
}
