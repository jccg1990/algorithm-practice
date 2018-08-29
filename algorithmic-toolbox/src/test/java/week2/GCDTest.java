package week2;

import org.junit.jupiter.api.Test;
import utils.Utils;

class GCDTest {

    @Test
    void stressTest() {
        while (true) {
            int x1 = Utils.getRandomInt(10000000, 100000000);
            int x2 = Utils.getRandomInt(10000000, 100000000);

            long res1 = GCD.gcd_naive(x1, x2);
            long res2 = GCD.gcd(x1, x2);

            System.out.println("GCD of: " + x1 + " and " + x2);
            if (res1 == res2) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + res1 + ", " + res2);
                return;
            }
        }
    }
}