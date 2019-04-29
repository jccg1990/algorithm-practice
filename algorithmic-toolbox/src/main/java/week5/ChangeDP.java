import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        ArrayList<Integer> coins = new ArrayList<>(Arrays.asList(1, 3, 4));
        int[] minC = new int[m + 1];

        minC[0] = 0;
        for (int i = 1; i <= m; i++) {
            minC[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= m; i++) {
            for (int c : coins) {
                if (i >= c) {
                    int numCoin = minC[i - c] + 1;
                    if (numCoin < minC[i]) minC[i] = numCoin;
                }
            }
        }

        return minC[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

