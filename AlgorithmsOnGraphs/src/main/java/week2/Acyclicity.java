package week2;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static ArrayList<Integer>[] adj;
    private static boolean[] startedVisit;
    private static boolean[] finishedVisit;

    private static int acyclic() {
        try {
            DFS();
        } catch (IllegalStateException e) {
            return 1;
        }
        return 0;
    }

    private static void explore(int v) {
        startedVisit[v] = true;

        for (int n : adj[v]) {
            if (startedVisit[n] && !finishedVisit[n]) throw new IllegalStateException("Cyclic");
            explore(n);
        }

        finishedVisit[v] = true;
    }

    private static void DFS() {
        finishedVisit = new boolean[adj.length];
        startedVisit = new boolean[adj.length];

        for (int i = 0; i < adj.length; i++) {
            if (!finishedVisit[i]) {
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
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic());
    }
}

