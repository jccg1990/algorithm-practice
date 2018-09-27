package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MajorityElement {
    public static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }

        int mid = ((right - left) / 2) + left;
        int lm = getMajorityElement(a, left, mid);
        int rm = getMajorityElement(a, mid, right);

        if (lm == rm) {
            return lm;
        } else if (lm != -1 && rm == -1) {
            if (isHalfMRealMajority(a, lm, left, right)) return lm;
        } else if (lm == -1) {
            if (isHalfMRealMajority(a, rm, left, right)) return rm;
        } else {
            if (isHalfMRealMajority(a, lm, left, right)) return lm;
            else if (isHalfMRealMajority(a, rm, left, right)) return rm;
        }

        return -1;
    }

    private static boolean isHalfMRealMajority(int[] a, int x, int left, int right) {
        int count = 0;
        for (int i = left; i < right; i++) {
            if (a[i] == x) {
                count++;
            }
        }

        if (count > (right - left) / 2) return true;
        else return false;
    }

    public static int getMajorityElementNaive(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int curr = a[i];
            int count = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[j] == curr) count++;
            }
            if (count > a.length / 2) return curr;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
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

