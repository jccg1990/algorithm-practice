import java.util.Scanner;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        return mergeSort(a, b, left, right - 1);
    }

    static long mergeSort(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += mergeSort(a, b, left, ave);
        numberOfInversions += mergeSort(a, b, ave + 1, right);
        numberOfInversions += merge(a, b, left, right);
        return numberOfInversions;
    }

    static long merge(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;

        int leftIndex = left;
        int leftEnd = (left + right) / 2;
        int rightIndex = leftEnd + 1;
        int bIndex = left;

        while (leftIndex <= leftEnd && rightIndex <= right) {
            int al = a[leftIndex];
            int ar = a[rightIndex];

            if (al <= ar) {
                b[bIndex] = al;
                leftIndex++;
            } else {
                b[bIndex] = ar;
                numberOfInversions += leftEnd + 1 - leftIndex;
                rightIndex++;
            }

            bIndex++;
        }

        while (leftIndex <= leftEnd) {
            b[bIndex] = a[leftIndex];
            leftIndex++;
            bIndex++;
        }

        while (rightIndex <= right) {
            b[bIndex] = a[rightIndex];
            rightIndex++;
            bIndex++;
        }

        while (left <= right) {
            a[left] = b[left];
            left++;
        }

        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];

        System.out.print(getNumberOfInversions(a, b, 0, a.length));
    }
}

