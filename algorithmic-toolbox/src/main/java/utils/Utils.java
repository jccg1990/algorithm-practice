package utils;

public class Utils {
    public static int getRandomInt(int min, int max) {
        int nr = (int) (min + (Math.random() * (max - min)));
        return nr;
    }
}
