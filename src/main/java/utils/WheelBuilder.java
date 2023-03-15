package utils;

import java.io.IOException;

import static utils.Library.*;

public class WheelBuilder {

    public static void main(String[] args) throws IOException {
        int i, j, count = 0;
        int[][] wheel = new int[b][k];
        while (!isEmpty(mSets)) {
            for (i = 0; i < mSetsCount; i++) {
                if (mSets[i] == null) {
                    continue;
                }
                for (j = 0; j < k; j++) {
                    wheel[count][j] = mSets[i][j];
                }
                for (j = 0; j < k; j++) {
                    System.out.print(wheel[count][j] + " ");
                }
                System.out.println();
                for (j = 0; j < mSetsCount; j++) {
                    if (mSets[j] != null && intersection(wheel[count], mSets[j]) >= t) {
                        mSets[j] = null;
                    }
                }
                count++;
            }
        }
    }
}