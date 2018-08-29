package week2;

import org.junit.jupiter.api.Test;
import utils.Utils;

class FibonacciTest {
    @Test
    void stressTest() {
        while (true) {
            int n = Utils.getRandomInt(0, 45);

            long res1 = Fibonacci.calc_fib(n);
            long res2 = Fibonacci.calc_fib_slow(n);

            System.out.println("Calculating the " + n + " number");
            if (res1 == res2) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + res1 + ", " + res2);
                return;
            }
        }
    }
}