package utils;

import java.io.IOException;

import static utils.Library.*;

public class Test {

    public static void main(String[] args) throws IOException {
        int v = 27, k = 6, m = 4, t = 3, b = 10;
        int times = 3;//((b * k) / v);
        int count = b - 1;
        int[][] wheel = readFromFile();
        System.out.println(isAnyNumberPresentAtMostNTimes(wheel, times, count, 3));
        System.out.println(areAnyTwoNumbersInAtMostOneKSet(wheel, count));
    }
}