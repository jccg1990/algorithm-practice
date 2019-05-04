import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class StackWithMax {
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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        StackM stack = new StackM();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
            } else if ("pop".equals(operation)) {
                stack.pop();
            } else if ("max".equals(operation)) {
                System.out.println(stack.max());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}

class StackM extends Stack {
    Stack<Integer> aux = new Stack<>();

    public Integer push(Integer item) {
        super.push(item);

        if (aux.isEmpty() || item.compareTo(aux.peek()) >= 0) {
            aux.push(item);
        }

        return item;
    }

    public Integer pop() {
        Integer obj = (Integer) super.pop();

        if (!aux.isEmpty() && obj.compareTo(aux.peek()) == 0) {
            aux.pop();
        }

        return obj;
    }

    public Integer max() {
        return aux.peek();
    }

}