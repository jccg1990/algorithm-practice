
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    class Node {
        private List<Node> children = new ArrayList<>();

        public void addChild(Node node) {
            children.add(node);
        }

        public List<Node> getChildren() {
            return children;
        }
    }

    public class TreeHeight {
        int n;
        int parent[];
        Node nodes[];
        Node root;

        void buildTree() {
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node();
            }

            for (int j = 0; j < n; j++) {
                int par = parent[j];
                if (par == -1) root = nodes[j];
                else nodes[par].addChild(nodes[j]);
            }
        }

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight(Node root) {
            int height = 0;

            if (root != null && root.children == null) height++;
            else {
                for (Node child : root.getChildren()) {
                    height = Math.max(height, computeHeight(child));
                }
            }

            return ++height;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        tree.buildTree();
        System.out.println(tree.computeHeight(tree.root));
    }
}
