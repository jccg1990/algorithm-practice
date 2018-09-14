package week3;

import org.junit.jupiter.api.Test;
import utils.Utils;
import week2.Fibonacci;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DotProductTest {
    @Test
    void stressTest() {
        while (true) {
            int[] x1 = new int[5];
            int[] x2 = new int[5];

            for (int i = 0; i < x1.length; ++i) {
                x1[i] = Utils.getRandomInt(-1000000, 1000000);
            }

            for (int i = 0; i < x2.length; ++i) {
                x2[i] = Utils.getRandomInt(-1000000, 1000000);
            }

            long res1 = DotProduct.maxDotProduct(x1, x2);
            long res2 = DotProduct.maxDotProductNaive(x1, x2);

            System.out.println("Calculating for " + Arrays.toString(x1) + " and " + Arrays.toString(x2));
            if (res1 == res2) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + res1 + ", " + res2);
                return;
            }
        }
    }

    @Test
    void oneTest(){
        int[] x1 = new int[]{0, 6, -2};
        int[] x2 = new int[]{-7, -5, 9};
        long res2 = DotProduct.maxDotProductNaive(x1, x2);
    }

}
