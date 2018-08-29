package week2;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.math.BigInteger;

class FibonacciLastDigitTest {

    @Test
    void stressTest() {
        while (true) {
            int n = Utils.getRandomInt(10, 10000);

            BigInteger res1 = FibonacciLastDigit.getFibonacciLastDigitNaive(n);
            long res2 = FibonacciLastDigit.getFibonacciLastDigit(n);

            System.out.println("Calculating the " + n + " number");
            if (res1.equals(new BigInteger(String.valueOf(res2)))) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + res1 + ", " + res2);
                return;
            }
        }
    }

}