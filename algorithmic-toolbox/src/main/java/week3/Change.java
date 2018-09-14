package week3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Change {
    public static int getChange(int m) {
        LinkedList<Integer> coins = new LinkedList<>(Arrays.asList(10, 5, 1));
        Integer count = 0;

        while (m > 0) {
            Integer ch = coins.pop();
            if (m >= ch) {
                count = count + (m / ch);
                m = m - (m / ch) * ch;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

