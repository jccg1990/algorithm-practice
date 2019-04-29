package week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Bipartite {
    static ArrayList<Integer>[] adj;
    static Boolean[] color;

    private static int bipartite() {
        try {
            BFS(0);
            return 1;
        } catch (IllegalStateException e) {
            return 0;
        }
    }

    private static void BFS(Integer s) {
        color[s] = Boolean.TRUE;

        LinkedList<Integer> q = new LinkedList<>();
        q.offer(s);

        while (!q.isEmpty()) {
            Integer u = q.poll();
            for (Integer v : adj[u]) {
                if (color[v] == null) {
                    q.offer(v);
                    color[v] = !color[u];
                } else if (color[u] == color[v]) {
                    throw new IllegalStateException();
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        color = new Boolean[n];
        adj = (ArrayList<Integer>[]) new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }

        System.out.println(bipartite());
    }
}

