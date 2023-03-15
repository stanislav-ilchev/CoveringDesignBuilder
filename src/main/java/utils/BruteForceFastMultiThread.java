package utils;

import java.io.FileWriter;
import java.io.IOException;

import static utils.Library.*;
import static utils.MultiThread.intersections;

public class BruteForceFastMultiThread implements Runnable {

    int i, j;
//    boolean[][] intersections;
    int[][] kSets = new int[kSetsCount][];
    int[] wheel = new int[b];


    public BruteForceFastMultiThread(boolean[][] intersections, int[][] kSets) {
//        for (i = 0; i < mSetsCount; i++) {
//            this.intersections[i] = new boolean[kSetsCount];
//        }
//        for (i = 0; i < mSetsCount; i++) {
//            for (j = 0; j < kSetsCount; j++) {
//                this.intersections[i][j] = intersections[i][j];
//            }
//        }
        for (i = 0; i < kSetsCount; i++) {
            this.kSets[i] = new int[k];
        }
//        this.intersections = intersections;
        for (i = 0; i < kSetsCount; i++) {
            for (j = 0; j < k; j++) {
                this.kSets[i][j] = kSets[i][j];
            }
        }
    }

    public void run() {

        test:
        while (true) {
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
            System.out.println(java.util.Calendar.getInstance().getTime());
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (i = 0; i < b; i++) {
                for (j = 0; j < k; j++) {
                    try {
                        fileWriter.append(kSets[wheel[i]][j] + " ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    fileWriter.append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            b--;
            return;
        }
    }
}