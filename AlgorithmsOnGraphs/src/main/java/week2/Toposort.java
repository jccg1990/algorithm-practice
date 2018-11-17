package week2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Toposort {
    private static ArrayList<Integer>[] adj;
    private static boolean[] visited;
    private static LinkedList<Integer> order = new LinkedList<>();

    private static List<Integer> toposort() {
        DFS();
        return order;
    }

    private static void explore(int v) {
        visited[v] = true;

        for (int n : adj[v]) {
            if (!visited[n]) explore(n);
        }

        order.push(v);
    }

    private static void DFS() {
        visited = new boolean[adj.length];

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                explore(i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        adj = (ArrayList<Integer>[]) new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }

        List<Integer> order = toposort();
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

