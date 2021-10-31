package utils;

import java.io.IOException;

import static utils.Library.*;

public class WheelBuilder {

    public static void main(String[] args) throws IOException {
        int i, j, count = 0;
        int[][] wheel = new int[b][k];
        while (!isEmpty(kSets)) {
            for (i = 0; i < kSetsCount; i++) {
                if (kSets[i] == null) {
                    continue;
                }
                for (j = 0; j < k; j++) {
                    wheel[count][j] = kSets[i][j];
                }
                for (j = 0; j < k; j++) {
                    System.out.print(wheel[count][j] + " ");
                }
                System.out.println();
                for (i = 0; i < kSetsCount; i++) {
                    if (kSets[i] != null && intersection(wheel[count], kSets[i]) >= t) {
                        kSets[i] = null;
                    }
                }
                count++;
            }
        }
    }
}