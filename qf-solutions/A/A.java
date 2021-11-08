import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int a = in.nextInt(), b = in.nextInt(), n = in.nextInt();
        // (n - b + b - a - 1) / (b - a) * 2 + 1
        System.out.println((n - a - 1) / (b - a) * 2 + 1);
    }
}

