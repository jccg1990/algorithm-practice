package week4;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
    private static ArrayList<Integer>[] adj;
    private static ArrayList<Integer>[] cost;
    private static Integer[] dist;

    private static int distance(int s, int t) {
        dijkstra(s);

        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
    }


    private static void dijkstra(int s) {
        dist = new Integer[adj.length];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }


        dist[s] = 0;

        PriorityQueue<Vertex> h = new PriorityQueue<>();

        for (int i = 0; i < dist.length; i++) {
            h.add(new Vertex(dist[i], i));
        }

        while (!h.isEmpty()) {
            Integer u = h.poll().getValue();
            ArrayList<Integer> uN = adj[u];
            ArrayList<Integer> wuN = cost[u];

            for (int i = 0; i < uN.size(); i++) {
                if (dist[uN.get(i)] > (long) dist[u] + wuN.get(i)) {
                    dist[uN.get(i)] = dist[u] + wuN.get(i);
                    h.add(new Vertex(dist[uN.get(i)], uN.get(i)));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        adj = (ArrayList<Integer>[]) new ArrayList[n];
        cost = (ArrayList<Integer>[]) new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }

        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;

        System.out.println(distance(x, y));
    }

    public static class Vertex implements Comparable<Vertex> {
        private Integer priority;
        private Integer value;

        Vertex(Integer p, Integer v) {
            this.priority = p;
            this.value = v;
        }

        Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(getPriority(), vertex.getPriority()) &&
                    Objects.equals(getValue(), vertex.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getPriority(), getValue());
        }

        @Override
        public int compareTo(Vertex o) {
            return this.priority.compareTo(o.getPriority());
        }
    }
}

