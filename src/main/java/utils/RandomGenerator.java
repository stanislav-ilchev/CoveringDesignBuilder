package utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class RandomGenerator {
    public static int i;
    public static int randomNumber;
    public static Random random = new Random();
    public static HashSet set = new HashSet<>();
    public static int numTries = 20000;

    public static void main(String[] args) throws IOException {
        for (i = 0; i < numTries; i++) {
            set.add(random.nextInt(10000));
        }
        System.out.println(set.size());
    }
}
