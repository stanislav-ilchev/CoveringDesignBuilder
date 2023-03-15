package utils;

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
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < kSetsCount; j++) {
                if (intersection(mSets[i], kSets[j]) >= t) {
                    intersections[i][j] = true;
                }
            }
        }
        date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        for (i = 0; i <= 11; i++) {
            Thread object = new Thread(new BruteForceFastMultiThread(intersections, kSets));
            object.start();
        }
    }
}