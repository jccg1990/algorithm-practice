package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.pow;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(long[] numbers) {
        long max_product = 0;
        int n = numbers.length;

        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                max_product = max(max_product,
                        numbers[first] * numbers[second]);
            }
        }

        return max_product;
    }

    static long getMaxPairwiseProductFast(long[] numbers) {
        int index1 = 0;
        int index2 = 0;
        int n = numbers.length;

        for (int i = 1; i < n; ++i) {
            if (numbers[i] > numbers[index1]) {
                index1 = i;
            }
        }

        if (index1 == 0) {
            index2 = 1;
        }

        for (int i = 0; i < n; ++i) {
            if (i != index1 && numbers[i] > numbers[index2]) {
                index2 = i;
            }
        }

        return (long) numbers[index1] * numbers[index2];
    }

    public static long[] generateFullArray() {
        long[] numbers = new long[(int) (2 * pow(10, 5))];
        for (int i = 0; i < 2 * pow(10, 5); ++i) {
            numbers[i] = i + 1;
        }
        return numbers;
    }

    public static void stressTest(int n, long m) {
        while (true) {
            int nr = 2 + (int) (Math.random() * (n - 2));

            long[] nums = new long[nr];

            System.out.println("");
            for (int i = 0; i < nr; ++i) {
                nums[i] = (long) (Math.random() * m);
                System.out.print(" " + nums[i] + " ");
            }
            System.out.println("");

            long res1 = getMaxPairwiseProduct(nums);
            long res2 = getMaxPairwiseProductFast(nums);

            if (res1 == res2) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + res1 + ", " + res2);
                return;
            }
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println(getMaxPairwiseProductFast(numbers));
//        stressTest(1000,200000);
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}