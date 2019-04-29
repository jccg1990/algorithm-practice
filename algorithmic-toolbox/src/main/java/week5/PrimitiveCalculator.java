package week5;

import java.util.*;
import java.util.function.Function;

import static java.lang.Integer.min;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    private static List<Integer> DPSequenceBetter(int n) {
        List<Integer> sequence = new ArrayList<>();

        int[] minSteps = new int[n + 1];

        for (int i = 1; i < minSteps.length; i++) {
            minSteps[i] = minSteps[i - 1] + 1;
            if (i % 2 == 0) {
                minSteps[i] = min(minSteps[i / 2] + 1, minSteps[i]);
            }
            if (i % 3 == 0) {
                minSteps[i] = min(minSteps[i / 3] + 1, minSteps[i]);
            }
        }

        for (int i = n; i > 1; ) {
            sequence.add(i);

            if (i % 3 == 0 && (minSteps[i / 3] == minSteps[i] - 1)) {
                i = i / 3;
            } else if (i % 2 == 0 && (minSteps[i / 2] == minSteps[i] - 1)) {
                i = i / 2;
            } else {
                i = i - 1;
            }
        }

        sequence.add(1);

        Collections.reverse(sequence);
        return sequence;
    }

    private static List<Integer> DPSequence(int n) {
        List<Integer> sequence = new ArrayList<>();
        int[] minSteps = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int x2 = Integer.MAX_VALUE;
            int x3 = Integer.MAX_VALUE;

            if (i % 3 == 0) {
                x3 = minSteps[i / 3] + 1;
            }
            if (i % 2 == 0) {
                x2 = minSteps[i / 2] + 1;
            }
            int xP1 = minSteps[i - 1] + 1;

            minSteps[i] = Collections.min(new ArrayList<>(Arrays.asList(x2, x3, xP1)));
        }

        while (n > 0) {
            int x2 = Integer.MAX_VALUE;
            int x3 = Integer.MAX_VALUE;

            sequence.add(n);

            if (n % 3 == 0) {
                x3 = minSteps[n / 3] + 1;
            }
            if (n % 2 == 0) {
                x2 = minSteps[n / 2] + 1;
            }
            int xP1 = minSteps[n - 1] + 1;

            int min = Collections.min(new ArrayList<>(Arrays.asList(x2, x3, xP1)));
            if (min == x3) {
                n /= 3;
            } else if (min == x2) {
                n /= 2;
            } else {
                n -= 1;
            }
        }


        Collections.reverse(sequence);
        return sequence;
    }

    private static List<Integer> DPSequence2(int n) {
        List<Function<Integer, Integer>> ops = new ArrayList<>(Arrays.asList((x) -> 2 * x, (x) -> 3 * x, (x) -> 1 + x));
        HashMap<Integer, List<Integer>> minO = new HashMap<>();

        minO.put(0, new ArrayList<>());
        minO.put(1, Collections.singletonList(1));

        for (int i = 1; i <= n; i++) {
            for (Function<Integer, Integer> fn : ops) {
                int cur = fn.apply(i);
                if (cur <= n && minO.get(cur) == null) {
                    List<Integer> cL = new ArrayList<>(minO.get(i));
                    cL.add(cur);
                    minO.put(cur, cL);
                } else if (cur <= n && minO.get(cur) != null) {
                    if (minO.get(cur).size() > minO.get(i).size() + 1) {
                        List<Integer> cL = new ArrayList<>(minO.get(i));
                        cL.add(cur);
                        minO.put(cur, cL);
                    }
                }
            }
        }

        return minO.get(n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = DPSequenceBetter(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

