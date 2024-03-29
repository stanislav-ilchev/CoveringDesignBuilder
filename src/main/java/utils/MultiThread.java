package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static utils.Library.*;

class MultiThread {

    public static boolean[][] intersections;

    public static void main(String[] args) {
        Date date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        int i, j;
        intersections = new boolean[mSetsCount][kSetsCount];
        int[][] kSets = buildCombinations(v, k);
        int[][] mSets = buildCombinations(v, m);

        Collections.shuffle(Arrays.asList(mSets));
        Collections.shuffle(Arrays.asList(kSets));

        for (i = 0; i < mSetsCount; i++) {
            Collections.shuffle(Arrays.asList(mSets[i]));
        }
        for (i = 0; i < kSetsCount; i++) {
            Collections.shuffle(Arrays.asList(kSets[i]));
        }

        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < kSetsCount; j++) {
                if (intersection(mSets[i], kSets[j]) >= t) {
                    intersections[i][j] = true;
                }
            }
        }
        date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        for (i = 1; i <= 12; i++) {
            Thread object = new Thread(new BruteForceFastMultiThread(intersections, kSets));
            object.start();
        }
    }
}