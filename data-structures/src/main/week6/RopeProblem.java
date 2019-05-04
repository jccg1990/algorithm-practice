
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class RopeProblem {

    public Vertex ropeRoot = null;

    class Rope {
        String s;

        public void process(int i, int j, int k) {
            i++;
            j++;
            k++;

            VertexPair leftPart = split(ropeRoot, i);
            Vertex leftOriginal = leftPart.left;
            Vertex leftWithSubstring = leftPart.right;

            VertexPair rightPart = split(leftWithSubstring, j - i + 2);
            Vertex subString = rightPart.left;
            Vertex rightOriginal = rightPart.right;

            Vertex original = merge(leftOriginal, rightOriginal);

            VertexPair insertionPoint = split(original, k);
            Vertex leftInsertion = insertionPoint.left;
            Vertex rightInsertion = insertionPoint.right;

            ropeRoot = merge(merge(leftInsertion, subString), rightInsertion);
        }

        public void processNaive( int i, int j, int k ) {
            if(s.isEmpty()) return;

            // Replace this code with a faster implementation
            String t = s.substring(0, i) + s.substring(j + 1);
            s = t.substring(0, k) + s.substring(i, j + 1) + t.substring(k);
        }

        void traverseTree() {
            int size = ropeRoot != null ? (int)ropeRoot.size : 0;
            StringBuilder builder = new StringBuilder(size);

            Stack<Vertex> stack = new Stack<>();
            Vertex curr = ropeRoot;

            while (!stack.isEmpty() || curr != null) {
                if (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                } else {
                    curr = stack.pop();
                    builder.append(curr.value);
                    curr = curr.right;
                }
            }

            s = builder.toString();
        }

        public void fillTree() {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                Vertex v = new Vertex(c, 1, null, null, null);

                if (ropeRoot != null) {
                    ropeRoot.parent = v;
                    v.left = ropeRoot;
                    ropeRoot = v;
                    update(ropeRoot);
                } else {
                    ropeRoot = v;
                }
            }
        }

        String result() {
            return s;
        }

        Rope(String s) {
            this.s = s;
            fillTree();
        }
    }

    public static void main(String[] args) throws IOException {
        new RopeProblem().run();
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        Rope rope = new Rope(in.next());
        for (int q = in.nextInt(); q > 0; q--) {
            int i = in.nextInt();
            int j = in.nextInt();
            int k = in.nextInt();
            rope.process(i, j, k);
        }
        rope.traverseTree();
        out.println(rope.result());
        out.close();
    }


    void update(Vertex v) {
        if (v == null) return;

        long left = v.left != null ? v.left.size : 0;
        long right = v.right != null ? v.right.size : 0;

        v.size = left + right + 1;

        if (v.left != null) {
            v.left.parent = v;
        }
        if (v.right != null) {
            v.right.parent = v;
        }
    }

    void smallRotation(Vertex v) {
        Vertex parent = v.parent;
        if (parent == null) {
            return;
        }
        Vertex grandparent = v.parent.parent;
        if (parent.left == v) {
            Vertex m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Vertex m = v.left;
            v.left = parent;
            parent.right = m;
        }
        update(parent);
        update(v);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
    }

    void bigRotation(Vertex v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            // Zig-zag
            smallRotation(v);
            smallRotation(v);
        }
    }

    // Makes splay of the given vertex and returns the new root.
    Vertex splay(Vertex v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    VertexPair find(Vertex root, long k) {
        return orderStatistic(root, k);


//        VertexPair pair = orderStatisticRecursive(null, root, k);
//
//        if (pair.right != null) {
//            pair.right = splay(pair.right);
//            return new VertexPair(pair.right, pair.right);
//        } else {
//            pair.left = splay(pair.left);
//            Vertex next = leftmost(pair.left);
//            return new VertexPair(next, pair.left);
//        }
    }

    Vertex leftmost(Vertex root) {
        if (root == null || root.right == null) {
            return null;
        }

        Vertex node = root.right;

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    VertexPair orderStatisticRecursive(Vertex prev, Vertex root, long k) {
        if (root == null) return new VertexPair(prev, root);
        long s = root.left != null ? root.left.size : 0;

        if (k == s + 1) {
            return new VertexPair(prev, root);
        } else if (k < s + 1) {
            return orderStatisticRecursive(root, root.left, k);
        } else {
            return orderStatisticRecursive(root, root.right, k - s - 1);
        }
    }

    VertexPair orderStatistic(Vertex root, long k) {
        if (root == null) return new VertexPair(null, null);

        Vertex v = root;
        Vertex last = root;
        Vertex next = null;

        while (v != null) {
            long s = v.left != null ? v.left.size : 0;

            if ((s + 1) >= k && (next == null || v.size < next.size)) {
                next = v;
            }
            last = v;

            if (k == (s + 1)) {
                break;
            }

            if (k < (s + 1)) {
                v = v.left;
            } else {
                v = v.right;
                k = (k - s - 1);
            }
        }

        root = splay(last);
        return new VertexPair(next, root);
    }

    VertexPair split(Vertex root, long k) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, k);
        root = findAndRoot.right;
        result.right = findAndRoot.left;
        if (result.right == null) {
            result.left = root;
            return result;
        }
        result.right = splay(result.right);
        result.left = result.right.left;
        result.right.left = null;
        if (result.left != null) {
            result.left.parent = null;
        }
        update(result.left);
        update(result.right);
        return result;
    }

    Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        while (right.left != null) {
            right = right.left;
        }
        right = splay(right);
        right.left = left;
        update(right);
        return right;
    }

    class Vertex {
        char value;
        long size;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(char value, long size, Vertex left, Vertex right, Vertex parent) {
            this.value = value;
            this.size = size;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    class VertexPair {
        Vertex left;
        Vertex right;

        VertexPair() {
        }

        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

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

}