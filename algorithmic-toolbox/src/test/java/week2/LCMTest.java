package week2;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.math.BigDecimal;

class LCMTest {

    @Test
    void stressTest() {
        while (true) {
            int x1 = Utils.getRandomInt(1000, 2000);
            int x2 = Utils.getRandomInt(1000, 2000);

            BigDecimal res1 = LCM.lcm_naive(x1, x2);
            BigDecimal res2 = LCM.lcm(x1, x2);

            System.out.println("Calculating the LCM of: " + x1 + " and " + x2 + ". Result is " + res2);
            if (res1.equals(res2)) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + res1 + ", " + res2);
                return;
            }
        }
    }
}