package week2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeapJava {
    private int[] data;
    private List<Swap> swaps = new ArrayList<>();

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeapJava().solve();
    }

    private void siftDown(Integer i) {
        int minIndex = i;
        int l = leftChild(i);
        int r = rightChild(i);

        if (l <= data.length - 1 && data[l] < data[minIndex]) minIndex = l;
        if (r <= data.length - 1 && data[r] < data[minIndex]) minIndex = r;

        if (i != minIndex) {
            swap(i, minIndex);
            siftDown(minIndex);
        }
    }

    private void buildHeap() {
        for (int i = data.length / 2; i >= 0; i--) {
            siftDown(i);
        }
    }

    private int rightChild(Integer i) {
        return 2 * i + 2;
    }

    private int leftChild(Integer i) {
        return 2 * i + 1;
    }

    private void swap(Integer i, Integer maxIndex) {
        swaps.add(new Swap(i, maxIndex));
        int tmp = data[i];
        data[i] = data[maxIndex];
        data[maxIndex] = tmp;
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }


    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        buildHeap();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
