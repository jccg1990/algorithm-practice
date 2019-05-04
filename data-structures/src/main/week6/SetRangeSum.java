package main.week6;

import java.io.*;
import java.util.StringTokenizer;

public class SetRangeSum {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    // Splay tree implementation

    // Vertex of a splay tree
    class Vertex {
        long key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        long sum;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(long key, long sum, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
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

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    VertexPair find(Vertex root, long key) {
        Vertex v = root;
        Vertex last = root;
        Vertex next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }
            last = v;
            if (v.key == key) {
                break;
            }
            if (v.key < key) {
                v = v.right;
            } else {
                v = v.left;
            }
        }
        root = splay(last);
        return new VertexPair(next, root);
    }

    VertexPair split(Vertex root, long key) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key);
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

    // Code that uses splay tree to solve the problem

    Vertex root = null;

    void insert(long x) {
        Vertex left = null;
        Vertex right = null;
        Vertex new_vertex = null;
        VertexPair leftRight = split(root, x);
        left = leftRight.left;
        right = leftRight.right;
        if (right == null || right.key != x) {
            new_vertex = new Vertex(x, x, null, null, null);
        }
        root = merge(merge(left, new_vertex), right);
    }

    void erase(long x) {
        if (!find(x)) return;

        delete(x);
    }

    void delete(long x) {
        if (!find(x)) return;

        splay(leftmost(root));
        splay(root);
        Vertex l = root.left;
        Vertex r = root.right;

        if (r != null) {
            r.left = l;
            if (l != null) l.parent = r;
            root.left = null;
            root.right = null;
            root = r;
            r.parent = null;
            update(root);
        } else if (l != null) {
            root.left = null;
            root = l;
            l.parent = null;
            update(root);
        } else {
            root.parent = null;
            root = null;
        }

    }

    //this is used after node x was found and rooted. we just look for the leftmost node on the right subtree
    Vertex leftmost(Vertex root) {
        if (root.right == null) {
            return null;
        }

        Vertex node = root.right;

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    boolean find(long x) {
        if (root == null) return false;

        VertexPair pair = find(root, x);

        Vertex prev = pair.left;
        Vertex node = pair.right;

        if (node != null) {
            root = node;
            return root.key == x;
        } else {
            root = prev;
            return  false;
        }


//        Vertex node = root;
//        Vertex prevNode = root;
//
//        //if node found result true, splay, root and break
//        while (node != null) {
//            if (node.key == x) {
//                root = splay(node);
//                root.parent = null;
//                return true;
//            } else if (x < node.key) {
//                prevNode = node;
//                node = node.left;
//            } else {
//                prevNode = node;
//                node = node.right;
//            }
//        }
//
//        root = splay(prevNode);
//        root.parent = null;
//
//        return false;
    }


    long sum(long from, long to) {
        VertexPair leftMiddle = split(root, from);
        Vertex left = leftMiddle.left;
        Vertex middle = leftMiddle.right;
        VertexPair middleRight = split(middle, to + 1);
        middle = middleRight.left;
        Vertex right = middleRight.right;
        long ans = 0;

        ans = middle != null ? middle.sum : 0;

        root = merge(merge(left, middle), right);

        return ans;
    }


    public static final long MODULO = 1000000001;

    void solve() throws IOException {
        long n = nextLong();
        long last_sum_result = 0;
        for (int i = 0; i < n; i++) {
            char type = nextChar();
            long x = 0;
            switch (type) {
                case '+': {
                    x = nextLong();
                    insert((x + last_sum_result) % MODULO);
                }
                break;
                case '-': {
                    x = nextLong();
                    erase((x + last_sum_result) % MODULO);
                }
                break;
                case '?': {
                    x = nextLong();
                    out.println(find((x + last_sum_result) % MODULO) ? "Found" : "Not found");
                }
                break;
                case 's': {
                    long l = nextLong();
                    long r = nextLong();
                    long res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO);
                    out.println(res);
                    last_sum_result = (res % MODULO);
                }
            }
//            out.println("*** " + i + " " + type + " " + (x + last_sum_result) % MODULO);
//            printTree(root);
//            out.println("");
        }
    }

    void printTree(Vertex node) {
        if (node == null) return;

        printTree(node.left);

        out.print(node.key + " -> ");

        printTree(node.right);

    }

    SetRangeSum() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/20")));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new SetRangeSum();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    char nextChar() throws IOException {
        return nextToken().charAt(0);
    }
}
