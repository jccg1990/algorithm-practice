package main.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class tree_orders {
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

    public class TreeOrders {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<>();

            Stack<Integer> s = new Stack<>();
            int i = 0;

            while (i != -1 || s.size() > 0) {

                while (i != -1) {
                    s.push(i);
                    i = left[i];
                }

                i = s.pop();
                result.add(key[i]);

                i = right[i];
            }

            return result;
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<>();

            Stack<Integer> s = new Stack<>();
            int i = 0;
            s.push(i);

            while (s.size() > 0) {
                i = s.pop();
                result.add(key[i]);

                if (right[i] != -1) s.push(right[i]);
                if (left[i] != -1) s.push(left[i]);
            }

            return result;
        }

        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<>();
            return posOrderHelper(0, result);
        }

        ArrayList<Integer> posOrderHelper(int i, ArrayList<Integer> result) {
            if (i == -1) return result;
            posOrderHelper(left[i], result);
            posOrderHelper(right[i], result);
            result.add(key[i]);
            return result;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
