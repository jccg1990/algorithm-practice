package week2;

import java.math.BigDecimal;
import java.util.Scanner;

public class LCM {
    public static BigDecimal lcm_naive(int a, int b) {
        BigDecimal aB = new BigDecimal(a);
        BigDecimal bB = new BigDecimal(b);

        for (BigDecimal l = BigDecimal.ONE; l.compareTo(aB.multiply(bB)) < 1; l = l.add(BigDecimal.ONE))
            if (l.remainder(aB).equals(BigDecimal.ZERO) && l.remainder(bB).equals(BigDecimal.ZERO))
                return l;

        return aB.multiply(bB);
    }

    public static BigDecimal lcm(int a, int b) {
        BigDecimal aB = new BigDecimal(a);
        BigDecimal bB = new BigDecimal(b);

        return aB.multiply(bB).divide(new BigDecimal(gcd(a, b)));
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(lcm(a, b));
    }
}
