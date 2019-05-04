import java.util.Scanner;

public class PlacingParentheses {
    static long[][] m;
    static long[][] M;
    static long[] nums;
    static char[] ops;

    private static long getMaximValue(String exp) {
        nums = new long[(exp.length() / 2) + 1];
        ops = new char[(exp.length() / 2)];

        for (int i = 0; i < exp.length(); i++) {
            if (i % 2 == 0) {
                nums[i / 2] = Character.getNumericValue(exp.charAt(i));
            } else {
                ops[(i / 2)] = exp.charAt(i);
            }
        }

        m = new long[nums.length][nums.length];
        M = new long[nums.length][nums.length];

        for (int i = 0; i < nums.length; i++) {
            m[i][i] = nums[i];
            M[i][i] = nums[i];
        }

        for (int s = 1; s < nums.length; s++) {
            for (int i = 0; i < nums.length - s; i++) {
                int j = i + s;
                long[] minMax = MinAndMax(i, j);
                m[i][j] = minMax[0];
                M[i][j] = minMax[1];
            }
        }

        return M[0][nums.length - 1];
    }

    static long[] MinAndMax(int i, int j) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (int k = i; k < j; k++) {
            long a = eval(M[i][k], M[k + 1][j], ops[k]);
            long b = eval(M[i][k], m[k + 1][j], ops[k]);
            long c = eval(m[i][k], M[k + 1][j], ops[k]);
            long d = eval(m[i][k], m[k + 1][j], ops[k]);
            min = Math.min(min, Math.min(Math.min(a, b), Math.min(c, d)));
            max = Math.max(max, Math.max(Math.max(a, b), Math.max(c, d)));
        }

        return new long[]{min, max};
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

