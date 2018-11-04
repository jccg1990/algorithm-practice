import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;
    private Worker[] workers;
    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void siftDown(Integer i) {
        int minIndex = i;
        int l = leftChild(i);
        int r = rightChild(i);

        if (l <= workers.length - 1 &&
                ((workers[l].priority < workers[minIndex].priority) ||
                        (workers[l].priority == workers[minIndex].priority && workers[l].index < workers[minIndex].index)))
            minIndex = l;

        if (r <= workers.length - 1 &&
                ((workers[r].priority < workers[minIndex].priority) ||
                        (workers[r].priority == workers[minIndex].priority && workers[r].index < workers[minIndex].index)))
            minIndex = r;

        if (i != minIndex) {
            swap(i, minIndex);
            siftDown(minIndex);
        }
    }

    private void incrementPriority(Integer i, long time) {
        workers[i].priority += time;

        siftDown(i);
    }

    private void swap(Integer i, Integer maxIndex) {
        Worker tmp = workers[i];
        workers[i] = workers[maxIndex];
        workers[maxIndex] = tmp;
    }

    private int rightChild(Integer i) {
        return 2 * i + 2;
    }

    private int leftChild(Integer i) {
        return 2 * i + 1;
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        workers = new Worker[numWorkers];

        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker(i, 0);
        }

        for (int i = 0; i < jobs.length; i++) {
            Worker curr = workers[0];
            assignedWorker[i] = curr.index;
            startTime[i] = curr.priority;
            incrementPriority(0, jobs[i]);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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

    static class Worker {
        private int index;
        private long priority;

        public Worker(int index, long priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
