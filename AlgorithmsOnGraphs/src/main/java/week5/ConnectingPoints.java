import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ConnectingPoints {

    public static class Point implements Comparable<Point> {

        private int x;
        private int y;
        private Double cost;
        private Point parent;

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

    static ArrayList<Point> points;

    private static double minimumDistance() {
        double result = 0.;

        for (Point p : points) {
            p.cost = Double.MAX_VALUE;
        }

        points.get(0).cost = 0.0;

        PriorityQueue<Point> q = new PriorityQueue<>(points);

        while (!q.isEmpty()) {
            Point v = q.poll();

            result += v.cost;

            for (Point z : new ArrayList<>(q)) {
                double cost = calculateCost(v, z);
                if (z.cost > cost) {
                    z.cost = cost;
                    z.parent = v;
                    changePriority(q, z);
                }
            }

        }

        return result;
    }

    private static void changePriority(PriorityQueue<Point> q, Point z) {
        q.remove(z);
        q.add(z);
    }

    private static double calculateCost(Point v, Point z) {
        return Math.sqrt(Math.pow(v.x - z.x, 2) + Math.pow(v.y - z.y, 2));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Point p = new Point();
            p.x = scanner.nextInt();
            p.y = scanner.nextInt();
            points.add(p);
        }

        System.out.println(minimumDistance());
    }
}

