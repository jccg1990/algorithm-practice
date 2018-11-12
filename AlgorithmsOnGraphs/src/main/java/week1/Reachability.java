
import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    private static ArrayList<Integer>[] adj;
    private static boolean[] visited;
    private static int[] ccNum;
    private static int cc;

    private static int reach(int x, int y) {
        int res = 0;
        DFS();

        if (ccNum[x] == ccNum[y]) res = 1;

        return res;
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

        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(x, y));
    }
}

