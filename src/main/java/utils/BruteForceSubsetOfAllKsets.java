package utils;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static utils.Library.*;

public class BruteForceSubsetOfAllKsets {

    public static void main(String[] args) throws IOException {
        int kSetsCount = 471;
        int i, j, count = 0, randomNumber;
        int[][] kSets = readFromFileNLines(kSetsCount, k);
        int[] wheel = new int[b];
        boolean[][] intersections = new boolean[mSetsCount][kSetsCount];
        List<Integer> usedNumbers = new ArrayList<>();
        long start = System.currentTimeMillis();
        Collections.shuffle(Arrays.asList(mSets));
        Collections.shuffle(Arrays.asList(kSets));
        for (i = 0; i < mSetsCount; i++) {
            Collections.shuffle(Arrays.asList(mSets[i]));
        }
        for (i = 0; i < kSetsCount; i++) {
            Collections.shuffle(Arrays.asList(kSets[i]));
        }
        XoRoShiRo128PlusRandom random = new XoRoShiRo128PlusRandom();
        for (i = 0; i < mSetsCount; i++) {
            for (j = 0; j < kSetsCount; j++) {
                if (intersection(mSets[i], kSets[j]) >= t) {
                    intersections[i][j] = true;
                }
            }
        }
        test2:
        while (true) {
            usedNumbers.clear();
//            count++;
//            if (count == 1000000) {
//                System.out.println(System.currentTimeMillis() - start);
//                start = System.currentTimeMillis();
//                count = 0;
//            }
            for (i = 0; i < b; i++) {
                randomNumber = random.nextInt(kSetsCount);
                while (usedNumbers.contains(randomNumber)) {
                    randomNumber = random.nextInt(kSetsCount);
                }
                wheel[i] = randomNumber;
                usedNumbers.add(randomNumber);
            }
            test:
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < b; j++) {
                    if (intersections[i][wheel[j]]) {
                        continue test;
                    }
                }
                continue test2;
            }
            System.out.println(b);
            try {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
//            return;
            b--;
            kSetsCount--;
            kSets = readFromFileNLines(kSetsCount, k);
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < kSetsCount; j++) {
                    intersections[i][j] = false;
                }
            }
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < kSetsCount; j++) {
                    if (intersection(mSets[i], kSets[j]) >= t) {
                        intersections[i][j] = true;
                    }
                }
            }
        }
    }
}