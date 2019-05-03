import java.io.*;
import java.util.*;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        long p = 11621L;
        long x = 1 + (long) (Math.random() * (p - 2));

        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();

        long patterHash = polyHash(s, p, x);
        long[] H = precomputeHashes(t, m, p, x);

        for (int i = 0; i <= n - m; ++i) {
            if (patterHash != H[i]) continue;
            else {
                if (areEquals(s, t, i, i + m)) {
                    occurrences.add(i);
                }
            }
        }

        return occurrences;
    }

    static boolean areEquals(String pattern, String s, int start, int end) {
        if (pattern.length() != end - start) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != s.charAt(start + i)) return false;
        }

        return true;
    }

    static long[] precomputeHashes(String T, int P, long p, long x) {
        long[] H = new long[T.length() - P + 1];
        String s = T.substring(T.length() - P);

        H[T.length() - P] = polyHash(s, p, x);

        long y = 1;

        for (int i = 0; i < P; i++) {
            y = mod((y * x), p);
        }

        for (int i = T.length() - P - 1; i >= 0; i--) {
            H[i] = mod((x * H[i + 1] + T.charAt(i) - y * T.charAt(i + P)), p);
        }

        return H;
    }

    static long mod(long a, long b) {
        return ((a % b) + b) % b;
    }

    static long polyHash(String s, long p, long x) {
        long hash = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            hash = mod((hash * x + s.charAt(i)), p);
        }

        return hash;
    }

    static class Data {
        String pattern;
        String text;

        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

