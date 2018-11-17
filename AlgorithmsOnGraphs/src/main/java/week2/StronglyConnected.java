package week2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class StronglyConnected {
    private static ArrayList<Integer>[] adj;
    private static ArrayList<Integer>[] adjR;
    private static boolean[] visited;
    private static ArrayList<Integer> order = new ArrayList<>();
    private static int cc;

    private static int numberOfStronglyConnectedComponents() {
        DFS(adjR);

        visited = new boolean[adj.length];

        for (int i = order.size() - 1; i >= 0; i--) {
            if (!visited[order.get(i)]) {
                explore(adj, order.get(i), false);
                cc++;
            }
        }

        return cc;
    }

    private static void DFS(ArrayList<Integer>[] adj) {
        visited = new boolean[adj.length];

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                explore(adj, i);
            }
        }
    }

    private static void explore(ArrayList<Integer>[] adj, int v){
        explore(adj, v, true);
    }

    private static void explore(ArrayList<Integer>[] adj, int v, boolean flag) {
        visited[v] = true;

        for (int n : adj[v]) {
            if (!visited[n]) explore(adj, n, flag);
        }

        if(flag){
           order.add(v);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        adj = (ArrayList<Integer>[]) new ArrayList[n];
        adjR = (ArrayList<Integer>[]) new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            adjR[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adjR[y - 1].add(x - 1);
        }

        System.out.println(numberOfStronglyConnectedComponents());
    }
}

