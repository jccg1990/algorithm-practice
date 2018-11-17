package week2;

import org.junit.jupiter.api.Test;
import utils.Utils;

class FibonacciTest {
    @Test
    void stressTest() {
        while (true) {
            int n = Utils.getRandomInt(0, 200);

            long res1 = Fibonacci.calc_fib(n);
            long res2 = Fibonacci.calcFib(n);

            System.out.println("Calculating the " + n + " number");
            if (res1 == res2) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + res1 + ", " + res2);
                return;
            }
        }
    }

    @Test
    void calcFibTest(){
        int n = 1000;


    }
}