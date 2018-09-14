import java.util.*;

public class FractionalKnapsack {
    public static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        List<Valuable> vList = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            vList.add(new Valuable(values[i], weights[i], ((double) values[i] / weights[i])));
        }
        Collections.sort(vList, Collections.reverseOrder());

        for (Valuable valuable : vList) {
            if (capacity == 0) return value;
            double a = Double.min(valuable.weight, capacity);
            value = value + a * valuable.vPerw;
            valuable.setWeight(valuable.weight - (int) a);
            capacity = capacity - (int) a;
        }

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    public static class Valuable implements Comparable<Valuable> {
        private int value;
        private int weight;
        private Double vPerw;

        public Valuable(int value, int weight, double vPerw) {
            this.value = value;
            this.weight = weight;
            this.vPerw = vPerw;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public double getvPerw() {
            return vPerw;
        }

        public void setvPerw(double vPerw) {
            this.vPerw = vPerw;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Valuable valuable = (Valuable) o;
            return getValue() == valuable.getValue() &&
                    getWeight() == valuable.getWeight() &&
                    getvPerw() == valuable.getvPerw();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue(), getWeight(), getvPerw());
        }


        @Override
        public int compareTo(Valuable o) {
            return vPerw.compareTo(o.getvPerw());
        }
    }
} 
