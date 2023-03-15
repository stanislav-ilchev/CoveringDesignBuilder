package utils;

import java.io.IOException;

import static utils.Library.*;

public class WheelBuilder3 {

    public static int[][][] mSetsTsets = new int[mSetsCount][tSetsInMsetsCount][t];

    public static void main(String[] args) throws IOException {
        int i, j, l, n, o, p, count = 0;
        int[][] wheel = new int[b][k];

        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < tSetsInMsetsCount; j++) {
                mSetsTsets[i][j] = getTsets(mSets[i], t)[j];
            }
        }

        while (!isEmpty(mSetsTsets)) {
            for (i = 0; i < mSetsCount; i++) {
                for (n = 0; n < tSetsInMsetsCount; n++) {
                    if (mSetsTsets[i][n] == null) {
                        continue;
                    }
                    wheel[count] = mSetsTsets[i][n];
                    for (j = 0; j < k; j++) {
                        System.out.print(wheel[count][j] + " ");
                    }
                    System.out.println();
                    for (j = 0; j < mSetsCount; j++) {
                        for (l = 0; l < tSetsInMsetsCount; l++) {
                            if (mSetsTsets[j][l] != null && intersection(wheel[count], mSetsTsets[j][l]) >= t) {
                                for (o = 0; o < mSetsCount; o++) {
                                    for (p = 0; p < tSetsInMsetsCount; p++) {
                                        if (mSetsTsets[o][p] != null && mSetsTsets[j][0] != null && intersection(mSetsTsets[j][0], mSetsTsets[o][p]) >= t) {
                                            mSetsTsets[o][p] = null;
                                        }
                                        if (mSetsTsets[o][p] != null && mSetsTsets[j][1] != null && intersection(mSetsTsets[j][1], mSetsTsets[o][p]) >= t) {
                                            mSetsTsets[o][p] = null;
                                        }
                                        if (mSetsTsets[o][p] != null && mSetsTsets[j][2] != null && intersection(mSetsTsets[j][2], mSetsTsets[o][p]) >= t) {
                                            mSetsTsets[o][p] = null;
                                        }
                                        if (mSetsTsets[o][p] != null && mSetsTsets[j][3] != null && intersection(mSetsTsets[j][3], mSetsTsets[o][p]) >= t) {
                                            mSetsTsets[o][p] = null;
                                        }
                                    }
                                }
                                mSetsTsets[j][0] = null;
                                mSetsTsets[j][1] = null;
                                mSetsTsets[j][2] = null;
                                mSetsTsets[j][3] = null;
                            }
                        }
                    }
                    count++;
                }
            }
        }
    }
}