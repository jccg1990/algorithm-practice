package week2.videos;

import java.time.Duration;
import java.time.LocalTime;

public class GCDMe {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println(gcd(357,234));
        System.out.println("Ran in " + Duration.between(now, LocalTime.now()));
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}
