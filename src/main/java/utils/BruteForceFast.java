package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import static utils.Library.*;

public class BruteForceFast {

    public static void main(String[] args) throws IOException {
        Date date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        int i, j, count = 0;
        int[] wheel = new int[b];
        byte[][] intersections = new byte[mSetsCount][kSetsCount];
        long start = System.currentTimeMillis();
        Collections.shuffle(Arrays.asList(kSets));
        Collections.shuffle(Arrays.asList(mSets));
        Random random = new Random();
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < kSetsCount; j++) {
                intersections[i][j] = (byte) intersection(mSets[i], kSets[j]);
            }
        }
        date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        test:
        while (kSetsCount > 0) {
//            count++;
//            if (count == 1000000) {
//                System.out.println(System.currentTimeMillis() - start);
//                start = System.currentTimeMillis();
//                count = 0;
//            }
            for (i = 0; i < b; i++) {
                wheel[i] = random.nextInt(kSetsCount);
            }
            test2:
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < b; j++) {
                    if (intersections[i][wheel[j]] >= t) {
                        continue test2;
                    }
                }
                continue test;
            }
            System.out.println(b);
            FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
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
            return;
//            b--;
        }
    }
}