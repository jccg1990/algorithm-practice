package week3;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    private HashTable hashTable;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
                hashTable.add(query.s);
                break;
            case "del":
                hashTable.remove(query.s);
                break;
            case "find":
                writeSearchResult(hashTable.find(query.s));
                break;
            case "check":
                hashTable.check(query.ind, out);
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        Integer bucketCount = in.nextInt();
        hashTable = new HashTable(bucketCount);
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
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

class HashTable {
    private LinkedList[] A;
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public HashTable(Integer buckets) {
        A = new LinkedList[buckets];
        bucketCount = buckets;
    }

    @SuppressWarnings("unchecked")
    public void add(String s) {
        int hash = hashFunc(s);
        LinkedList<String> l = A[hash] != null ? A[hash] : (A[hash] = new LinkedList<>());
        for (String elem : l) {
            if (elem.equals(s)) return;
        }
        l.addFirst(s);
    }

    @SuppressWarnings("unchecked")
    public boolean find(String s) {
        int hash = hashFunc(s);
        LinkedList<String> l = A[hash] != null ? A[hash] : (A[hash] = new LinkedList<>());
        for (String elem : l) {
            if (elem.equals(s)) return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public void remove(String s) {
        if (!find(s)) return;
        int hash = hashFunc(s);
        LinkedList<String> l = A[hash] != null ? A[hash] : (A[hash] = new LinkedList<>());
        l.remove(s);
    }

    @SuppressWarnings("unchecked")
    public void check(Integer i, PrintWriter out) {
        LinkedList<String> l = A[i] != null ? A[i] : (A[i] = new LinkedList<>());
        for (String cur : l) {
            out.print(cur + " ");
        }
        out.println();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int) hash % bucketCount;
    }

}
