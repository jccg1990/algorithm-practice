
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BinarySearch {

    static int binarySearch(HashMap<Integer, Integer> a, int x) {
        return a.getOrDefault(x, -1);

//        int left = 0, right = a.length - 1;
//
//        while (left <= right) {
//            int middle = ((right - left) / 2) + left;
//            if (x == a[middle]) {
//                return middle;
//            } else if (x < a[middle]) {
//                right = middle - 1;
//            } else {
//                left = middle + 1;
//            }
//        }
//
//        return -1;
    }

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer, Integer> a = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a.put(scanner.nextInt(), i);
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(a.getOrDefault(b[i], -1) + " ");
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
