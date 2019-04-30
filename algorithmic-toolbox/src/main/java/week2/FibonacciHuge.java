package week2;

import java.math.BigDecimal;
import java.util.Scanner;

public class FibonacciHuge {
    public static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }

    public static BigDecimal getFibonacciHuge(long n, long m) {
        if (n <= 1) return new BigDecimal(n);
        if (m <= 1) return BigDecimal.ZERO;

        long period = calculatePisanoPeriod(m);
        long remainder = n % period;

        if (remainder < 2) return new BigDecimal(remainder);

        BigDecimal first = BigDecimal.ZERO;
        BigDecimal second = BigDecimal.ONE;
        BigDecimal res = BigDecimal.ZERO;

        for (int i = 2; i <= remainder; i++) {
            res = first.add(second);
            first = second;
            second = res;
        }

        return res.remainder(new BigDecimal(m));
    }

    public static long calculatePisanoPeriod(long m) {
        long i = 0, j = 1, counter = 0, k;

        do {
            k = (i + j) % m;
            i = j;
            j = k;
            counter++;
        } while (!(i == 0 && j == 1));

        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }
}

