package week3;

import org.junit.jupiter.api.Test;
import utils.Utils;

class ChangeTest {
    @Test
    void stressTest() {
        while (true) {
            int n = Utils.getRandomInt(1, 30);

            System.out.println("Trying for " + n);
            int change = Change.getChange(n);
            System.out.println("Coins for " + n + " are: " + change);
        }
    }

}