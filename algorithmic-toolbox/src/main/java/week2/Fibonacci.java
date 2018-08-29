package week2;

import java.util.Scanner;

public class Fibonacci {
    public static long calc_fib_slow(int n) {
        if (n <= 1)
            return n;

        return calc_fib_slow(n - 1) + calc_fib_slow(n - 2);
    }

    public static long calc_fib(int n) {
        if (n <= 1)
            return n;

        int[] fibArray = new int[n + 1];

        fibArray[0] = 0;
        fibArray[1] = 1;

        for (int i = 2; i <= n; ++i) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }

        return fibArray[n];
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(calc_fib(n));
    }
}
