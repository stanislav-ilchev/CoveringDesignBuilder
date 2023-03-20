package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import static utils.Library.*;

public class BruteForceFast {

    public static void main(String[] args) {
        Date date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        int i, j, count = 0;
        int[] wheel = new int[b];
        boolean[][] intersections = new boolean[mSetsCount][kSetsCount];
        long start = System.currentTimeMillis();

        Collections.shuffle(Arrays.asList(mSets));
        Collections.shuffle(Arrays.asList(kSets));
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
        test:
        while (kSetsCount > 0) {
            count++;
            if (count == 1000000) {
                System.out.println(System.currentTimeMillis() - start);
                start = System.currentTimeMillis();
                count = 0;
            }
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
            System.out.println(b);
            try {
                FileWriter fileWriter = new FileWriter("C:\\Users\\stanislav.ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                for (i = 0; i < b; i++) {
                    for (j = 0; j < k; j++) {
//                    System.out.print(kSets[wheel[i]][j] + " ");
                        fileWriter.append(kSets[wheel[i]][j] + " ");
                    }
//                System.out.println();
                    fileWriter.append("\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
//            b--;
        }
    }
}