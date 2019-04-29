package week2;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.math.BigDecimal;

class FibonacciHugeTest {
    @Test
    void stressTest() {
        while (true) {
            int n = Utils.getRandomInt(0, 45);
            int m = Utils.getRandomInt(1, 10);

            System.out.println("Module " + m + " of " + n + " number");

            long res1 = FibonacciHuge.getFibonacciHugeNaive(n, m);
            BigDecimal res2 = FibonacciHuge.getFibonacciHuge(n, m);

            if (res2.compareTo(new BigDecimal(res1)) == 0) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + res1 + ", " + res2);
                return;
            }
        }
    }

    @Test
    void getFibonacciHugeTest() {
        int n = 1000;
        int m = 100;

        System.out.println("Module " + m + " of " + n + " number");

        long res1 = FibonacciHuge.getFibonacciHugeNaive(n, m);
        BigDecimal res2 = FibonacciHuge.getFibonacciHuge(n, m);

        if (res2.compareTo(new BigDecimal(res1)) == 0) {
            System.out.println("OK " + res2);
        } else {
            System.out.println("Wrong answer: " + res1 + ", " + res2);
            return;
        }
    }

    @Test
    void calculatePisanoPeriodTest() {
        while (true) {
            long[] knowPeriods = {0, 1, 3, 8, 6, 20, 24, 16, 12, 24, 60, 10, 24};
            int m = Utils.getRandomInt(2, 12);

            long res = FibonacciHuge.calculatePisanoPeriod(m);

            System.out.println("Period of " + m);
            if (res == knowPeriods[m]) System.out.println("OK");
            else {
                System.out.println("Wrong answer: " + res + ", " + knowPeriods[m]);
                return;
            }
        }
    }
}