package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static utils.Library.*;

class MultiThread {
    public static void main(String[] args) {
        Date date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        int i, j;
        byte[][] intersections = new byte[mSetsCount][kSetsCount];
        Collections.shuffle(Arrays.asList(kSets));
        Collections.shuffle(Arrays.asList(mSets));
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < kSetsCount; j++) {
                intersections[i][j] = (byte) intersection(mSets[i], kSets[j]);
            }
        }
        date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        for (i = 0; i < 12; i++) {
            Thread object = new Thread(new BruteForceFastMultiThread(intersections));
            object.start();
        }
    }
}