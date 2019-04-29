package week4;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.Arrays;

class SortingTest {

    @Test
    void stressTest() {
        while (true) {
            int x1 = Utils.getRandomInt(5, 10);
            int a[] = new int[x1];

            for (int i = 0; i < x1; i++) {
                a[i] = Utils.getRandomInt(0, 5);
            }

            int b[] = new int[a.length];
            System.arraycopy(a, 0, b, 0, a.length);

            System.out.println("Sorting: " + Arrays.toString(a) + "should b equals to: " + Arrays.toString(b));

            Sorting.randomizedQuickSort2(a, 0, a.length - 1);
            Sorting.randomizedQuickSort3(b, 0, b.length - 1);

            if (Arrays.equals(a, b)) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + Arrays.toString(a) + ", " + Arrays.toString(b));
                return;
            }
        }
    }
}