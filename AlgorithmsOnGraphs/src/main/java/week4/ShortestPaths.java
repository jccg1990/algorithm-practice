import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ShortestPaths {

    static ArrayList<Integer>[] adj;
    static ArrayList<Integer>[] cost;
    static long[] distance;
    static int[] reachable;
    static int[] shortest;
    static HashSet<Integer> relaxedNodes;
    static ArrayDeque<Integer> bfsQ;

    private static void shortestPaths(int s, int vLength) {
        relaxedNodes = new HashSet<>();
        bfsQ = new ArrayDeque<>();
        bellmanFord(s, vLength);
        BFS();
    }

    static void BFS() {
        while (!bfsQ.isEmpty()) {
            int u = bfsQ.poll();
            for (Integer v : adj[u]) {
                if (shortest[v] == 1) {
                    bfsQ.offer(v);
                    shortest[v] = 0;
                }
            }
        }
    }

    static void bellmanFord(int s, int vLength) {
        distance[s] = 0;
        reachable[s] = 1;

        for (int i = 0; i <= vLength; i++) {
            for (int from = 0; from < adj.length; from++) {
                for (int j = 0; j < adj[from].size(); j++) {
                    int to = adj[from].get(j);
                    int weight = cost[from].get(j);

                    boolean relaxed = relax(from, to, weight);

                    if (relaxed && i == vLength) {
                        if (!relaxedNodes.contains(from)) {
                            relaxedNodes.add(from);
                            bfsQ.add(from);
                        }
                        if (!relaxedNodes.contains(to)) {
                            relaxedNodes.add(to);
                            bfsQ.add(to);
                        }
                    }
                }
            }
        }
    }

    static boolean relax(int from, int to, int weight) {
        long realDis = distance[from] == Long.MAX_VALUE ? Long.MAX_VALUE : distance[from] + weight;

        if (distance[to] > realDis) {
            distance[to] = realDis;
            reachable[to] = 1;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
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
        int s = scanner.nextInt() - 1;
        distance = new long[n];
        reachable = new int[n];
        shortest = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(s, m);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}

