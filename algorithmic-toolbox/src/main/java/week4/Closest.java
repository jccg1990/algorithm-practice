import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }

    static double minimalDistance(int[] x, int y[]) {
        Point[] arr = new Point[x.length];

        for (int i = 0; i < x.length; i++) {
            arr[i] = new Point(x[i], y[i]);
        }

        Arrays.sort(arr, (a, b) -> Long.signum(a.x - b.x));

        return calculateDistance(arr, 0, arr.length - 1);
    }

    static double calculateDistance(Point[] arr, int left, int right) {
        if (left >= right) {
            return Double.POSITIVE_INFINITY;
        }

        int middle = ((right - left) / 2) + left;

        double d = Math.min(calculateDistance(arr, left, middle), calculateDistance(arr, middle + 1, right));

        ArrayList<Point> points = new ArrayList<>();
        int offset = 0;
        while (middle - offset >= left && Math.abs(arr[middle].x - arr[middle - offset].x) < d) {
            points.add(arr[middle - offset]);
            offset++;
        }

        offset = 1;
        while (middle + offset <= right && Math.abs(arr[middle + offset].x - arr[middle].x) < d) {
            points.add(arr[middle + offset]);
            offset++;
        }

        Collections.sort(points);

        for (int i = 0; i < points.size(); i++) {
            Point a = points.get(i);

            for (int j = i + 1; j <= i + 7 && j < points.size(); j++) {
                Point b = points.get(j);
                double distance = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
                d = Math.min(d, distance);
            }
        }

        return d;
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
