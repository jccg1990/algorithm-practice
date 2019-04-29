package week6;

import java.util.Scanner;

public class Partition3 {
    private static int partition3(int[] A) {
        int sum = 0;

        for (int vA : A) {
            sum += vA;
        }

        boolean dp[][][] = new boolean[A.length + 1][sum + 1][sum + 1];

        if (sum % 3 == 0) {
            for (int i = 1; i <= A.length; i++) {
                dp[i][0][0] = dp[i][A[i - 1]][0] = dp[i][0][A[i - 1]] = true;
            }

            for (int i = 2; i <= A.length; i++) {
                for (int j = 1; j <= (sum / 3) + 1; j++) {
                    for (int k = 1; k <= (sum / 3) + 1; k++) {
                        dp[i][j][k] = dp[i - 1][j][k];
                        if (j >= A[i - 1]) dp[i][j][k] = dp[i][j][k] || dp[i - 1][j - A[i - 1]][k];
                        if (k >= A[i - 1]) dp[i][j][k] = dp[i][j][k] || dp[i - 1][j][k - A[i - 1]];
                    }
                }
            }
        }

        return dp[A.length][sum / 3][sum / 3] ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

