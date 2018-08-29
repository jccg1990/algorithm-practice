package week2;

import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciLastDigit {
    public static BigInteger getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return new BigInteger(String.valueOf(n));

        BigInteger previous = BigInteger.ZERO;
        BigInteger current = BigInteger.ONE;

        for (int i = 0; i < n - 1; ++i) {
            BigInteger tmp_previous = new BigInteger(String.valueOf(previous));
            previous = current;
            current = tmp_previous.add(current);
        }

        return current.mod(BigInteger.TEN);
    }

    public static int getFibonacciLastDigit(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
        }

        return current;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}

