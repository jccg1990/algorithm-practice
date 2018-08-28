package week2;

import java.time.Duration;
import java.time.LocalTime;

public class Fibonacci {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println(FibList(200));
        System.out.println("Ran in " + Duration.between(now, LocalTime.now()));
    }

    public static long FibRecurs(long n) {
        if (n <= 1)
            return n;
        else
            return FibRecurs(n - 1) + FibRecurs(n - 2);

    }

    public static long FibList(int n) {
        if (n <= 1)
            return n;
        else {
            long[] ar = new long[n];
            ar[0] = 0;
            ar[1] = 1;
            for(int i =2; i < n; ++i){
                ar[i] = ar[i-1] + ar[i-2];
            }
            return ar[n-1];
        }
    }
}
