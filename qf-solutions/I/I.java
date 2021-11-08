import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int xl = Integer.MAX_VALUE;
        int xr = Integer.MIN_VALUE;
        int yl = Integer.MAX_VALUE;
        int yr = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(), y = in.nextInt(), h = in.nextInt();
            xl = Math.min(xl, x - h);
            yl = Math.min(yl, y - h);
            xr = Math.max(xr, x + h);
            yr = Math.max(yr, y + h);
        }
        int h = (Math.max(xr - xl, yr - yl) + 1) / 2;
        int x = (xl + xr) / 2;
        int y = (yl + yr) / 2;

        System.out.println(x + " " + y + " " + h);
    }
}
