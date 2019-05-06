import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Clustering {
    static HashSet<Point> allPoints = new HashSet<>();
    static HashSet<Point> set = new HashSet<>();
    static PriorityQueue<Point> pq = new PriorityQueue<>();

    private static double clustering(int[] x, int[] y, int k) {
        for (int i = 0; i < x.length; i++) {
            Point p = new Point(x[i], y[i]);

            if (i == 0) {
                p.cost = 0.0;
            }

            addToDS(p);
        }

        int clusterNumber = set.size();

        buildMST();

        pq = new PriorityQueue<>(allPoints);

        while (!pq.isEmpty()) {
            Point v = pq.poll();

            if (!find(v).equals(find(v.neighbour))) {
                union(v, v.neighbour);
                clusterNumber--;
            }

            if (clusterNumber == k) {
                break;
            }
        }

        return pq.peek() != null ? pq.peek().cost : 0;
    }

    private static void buildMST() {
        while (!pq.isEmpty()) {
            Point v = pq.poll();
            set.remove(v);

            for (Point z : set) {
                double cost = calculateCost(v, z);
                if (z.cost > cost) {
                    z.cost = cost;
                    z.neighbour = v;
                    changePriority(pq, z);
                }
            }
        }
    }

    private static void addToDS(Point p) {
        allPoints.add(p);
        set.add(p);
        pq.add(p);
    }

    private static void changePriority(PriorityQueue<Point> q, Point z) {
        q.remove(z);
        q.add(z);
    }

    private static double calculateCost(Point v, Point z) {
        return Math.sqrt(Math.pow(v.x - z.x, 2) + Math.pow(v.y - z.y, 2));
    }

    static Point find(Point p) {
        if (p.parent.equals(p)) return p;

        Point parent = find(p.parent);
        p.parent = parent;

        return parent;
    }

    static void union(Point a, Point b) {
        if (a == null || b == null) return;

        Point pa = find(a);
        Point pb = find(b);

        pa.parent = pb;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        Double cost = Double.MAX_VALUE;
        Point parent;
        Point neighbour;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.parent = this;
            this.neighbour = this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Point o) {
            return this.cost.compareTo(o.cost);
        }
    }
}

