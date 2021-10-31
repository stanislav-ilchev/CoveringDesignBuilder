package utils;

import static utils.Library.*;

public class Rotation {

    public static void main(String[] args) {
        int i, j, start = 0, increase, lastNumber = 0;
        for (i = 1; i <= b; i++) {
            increase = 0;
            for (j = 0; j < k; j++) {
                if (start + increase == v) {
                    start = 0;
                    increase = 0;
                }
                System.out.print((start + increase) + " ");
                lastNumber = start + increase;
                increase++;
            }
            System.out.println();
            start = lastNumber - t + 1;
        }
    }
}
