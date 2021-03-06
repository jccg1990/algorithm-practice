package week1;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static ArrayList<Integer>[] adj;
    private static boolean[] visited;
    private static int[] ccNum;
    private static int cc;

    private static int numberOfComponents() {
        DFS();
        return cc;
    }

    private static void explore(int v) {
        visited[v] = true;
        ccNum[v] = cc;

        for (int n : adj[v]) {
            if (!visited[n]) explore(n);
        }
    }

    private static void DFS() {
        visited = new boolean[adj.length];
        ccNum = new int[adj.length];

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                explore(i);
                cc++;
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
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents());
    }
}

