package week2;

import java.util.Scanner;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }

    public static long getFibonacciPartialSum(long n, long m) {
        long max = getFibonacciSum(m);
        long min = getFibonacciSum(n - 1);

        max = max >= min ? max : max + 10;

        return max - min;
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
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
    }
}

