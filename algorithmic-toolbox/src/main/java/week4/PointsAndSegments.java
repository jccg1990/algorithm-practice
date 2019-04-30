import java.util.PriorityQueue;
import java.util.Scanner;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];

        PriorityQueue<Point> queue = new PriorityQueue<>();

        for (int i = 0; i < starts.length; i++) {
            Point start = new Point();
            start.type = TYPE.START;
            start.value = starts[i];

            Point end = new Point();
            end.type = TYPE.END;
            end.value = ends[i];

            queue.add(start);
            queue.add(end);
        }

        for (int i = 0; i < points.length; i++) {
            Point point = new Point();
            point.value = points[i];
            point.idx = i;
            point.type = TYPE.POINT;

            queue.add(point);
        }

        int counter = 0;
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.type == TYPE.START) counter++;
            else if (current.type == TYPE.END) counter--;
            else cnt[current.idx] = counter;

        }

        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

enum TYPE {
    START, POINT, END
}

class Point implements Comparable<Point> {
    TYPE type;
    Integer value;
    Integer idx;

    @Override
    public int compareTo(Point o) {
        if (this.value.compareTo(o.value) == 0) {
            return this.type.ordinal() - o.type.ordinal();
        } else {
            return this.value.compareTo(o.value);
        }
    }
}
