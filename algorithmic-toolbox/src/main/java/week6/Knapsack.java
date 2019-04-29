package week6;

import java.util.Scanner;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int[][] mW = new int[W + 1][w.length + 1];

        for (int k = 1; k <= W; k++) {
            for (int i = 0; i < w.length; i++) {
                int max = mW[k][i];
                if (w[i] <= k) {
                    int x1 = mW[k - w[i]][i] + w[i];
                    max = Math.max(x1, max);
                }
                if (max > mW[k][i + 1]) mW[k][i + 1] = max;
            }
        }

        return mW[W][w.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

