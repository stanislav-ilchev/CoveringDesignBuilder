package utils;

import com.aparapi.Kernel;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import static utils.Library.*;

public class BruteForceFastAparapi {

    public static void main(String[] args) throws IOException {
        Date date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        int i, j, count = 0;
        int[] wheel = new int[b];
        boolean[][] intersections = new boolean[mSetsCount][kSetsCount];
        long start = System.currentTimeMillis();

//        Collections.shuffle(Arrays.asList(mSets));
//        Collections.shuffle(Arrays.asList(kSets));
        Random random = new Random();
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < kSetsCount; j++) {
                if (intersection(mSets[i], kSets[j]) >= t) {
                    intersections[i][j] = true;
                }
            }
        }
        date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        Kernel kernel = new Kernel() {
            int i = getGlobalId(), j;

            @Override
            public void run() {
                test:
                while (kSetsCount > 0) {
                    for (i = 0; i < b; i++) {
                        wheel[i] = random.nextInt(kSetsCount);
                    }
                    test2:
                    for (i = 0; i < mSetsCount; i++) {
                        for (j = 0; j < b; j++) {
                            if (intersections[i][wheel[j]]) {
                                continue test2;
                            }
                        }
                        continue test;
                    }
                    for (i = 0; i < b; i++) {
                        for (j = 0; j < k; j++) {
                            System.out.print(kSets[wheel[i]][j] + " ");
                        }
                        System.out.println();
                    }
                    return;
                }
            }
        };
        kernel.execute(mSetsCount);
    }
}