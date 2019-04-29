package week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BFS {
    static Integer[] dist = null;
    static Integer[] prev = null;
    static ArrayList<Integer>[] adj;

    private static int distance(int s, int t) {
        BFS(s);

        return dist[t];
    }

    private static void BFS(Integer s) {
        for (int i = 0; i < dist.length; i++) {
            dist[i] = -1;
        }

        dist[s] = 0;

        LinkedList<Integer> q = new LinkedList<>();
        q.offer(s);

        while (!q.isEmpty()) {
            Integer u = q.poll();
            for (Integer v : adj[u]) {
                if (dist[v] == -1) {
                    q.offer(v);
                    dist[v] = dist[u] + 1;
                    prev[v] = u;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        dist = new Integer[n];
        prev = new Integer[n];
        adj = (ArrayList<Integer>[]) new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }

        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(x, y));
    }
}

