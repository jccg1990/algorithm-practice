package week2;

import java.util.Scanner;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;
        long sum = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    public static long getFibonacciSum(long n) {
        long res = getFibonacci(n + 2);

        res = res == 0 ? 10 : res;

        return res - 1;
    }

    public static long getFibonacci(long n) {
        if (n <= 1) return n;

        long period = 60;
        long remainder = n % period;

        if (remainder < 2) return remainder;

        long first = 0;
        long second = 1;
        long res = 0;

        for (int i = 2; i <= remainder; i++) {
            res = (first + second) % 10;
            first = second;
            second = res;
        }

        return res;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSum(n);
        System.out.println(s);
    }
}

