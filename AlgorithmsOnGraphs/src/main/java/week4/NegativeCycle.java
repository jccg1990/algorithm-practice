package week4;

import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static ArrayList<Integer>[] adj;
    private static ArrayList<Integer>[] cost;
    private static Integer[] dist;

    private static int negativeCycle() {
        boolean cycleFound = bellmanFord(0);

        return cycleFound ? 1 : 0;
    }

    private static boolean bellmanFord(int s) {
        dist = new Integer[adj.length];
        boolean flag = false;

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[s] = 0;

        for (int i = 1; i <= adj.length; i++) {
            for (int u = 0; u < adj.length; u++) {
                for (int v : adj[u]) {
                    boolean relaxed = relax(u, v);
                    if (i == adj.length && relaxed) {
                        flag = true;
                    }
                }
            }
        }

        return flag;
    }

    private static boolean relax(int u, int v) {
        boolean flag = false;

        int i = adj[u].indexOf(v);
        int w = cost[u].get(i);

        if (dist[v] > (long) dist[u] + w) {
            dist[v] = dist[u] + w;
            flag = true;
        }

        return flag;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        adj = (ArrayList<Integer>[]) new ArrayList[n];
        cost = (ArrayList<Integer>[]) new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }

        System.out.println(negativeCycle());
    }
}

