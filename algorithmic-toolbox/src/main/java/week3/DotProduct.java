package week3;

import java.util.*;
import java.util.stream.Collectors;

public class DotProduct {
    public static long maxDotProduct(int[] a, int[] b) {


        LinkedList<Integer> aList = new LinkedList<>(Arrays.stream(a).boxed().collect(Collectors.toList()));
        LinkedList<Integer> bList = new LinkedList<>(Arrays.stream(b).boxed().collect(Collectors.toList()));

        Collections.sort(aList, Collections.reverseOrder());
        Collections.sort(bList, Collections.reverseOrder());

        long result = 0;

        while (aList.size() > 0) {
            if (aList.peek() < 0 && bList.peek() < 0) break;
            result += (long) aList.pop() * bList.pop();
        }

        while (aList.size() > 0) {
            result += (long) aList.removeLast() * bList.removeLast();
        }

        return result;
    }

    public static long maxDotProductNaive(int[] a, int[] b) {
        long result = 0;

        LinkedList<Integer> aList = new LinkedList<>(Arrays.stream(a).boxed().collect(Collectors.toList()));
        LinkedList<Integer> bList = new LinkedList<>(Arrays.stream(b).boxed().collect(Collectors.toList()));

        Collections.sort(aList, Collections.reverseOrder());
        Collections.sort(bList, Collections.reverseOrder());

        while (aList.size() > 0) {
            long max = Long.MIN_VALUE;
            if (aList.peek() <= 0) break;
            Integer bRem = null;
            for (int j = 0; j < bList.size(); ++j) {
                long curr = (long) aList.peek() * bList.get(j);
                if (curr > max) {
                    max = curr;
                    bRem = bList.get(j);
                }
            }
            result += max;
            bList.remove(bRem);
            aList.pop();
        }


        Collections.sort(aList);

        while (aList.size() > 0) {
            long max = Long.MIN_VALUE;
            Integer bRem = null;
            for (int j = 0; j < bList.size(); ++j) {
                long curr = (long) aList.peek() * bList.get(j);
                if (curr > max) {
                    max = curr;
                    bRem = bList.get(j);
                }
            }
            result += max;
            bList.remove(bRem);
            aList.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

