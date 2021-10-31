package utils;

import java.io.FileWriter;
import java.io.IOException;

import static utils.Library.*;

public class BruteForceFastMultiThread implements Runnable {

    byte[][] intersections;
    int[] wheel = new int[b];
    int i, j;

    public BruteForceFastMultiThread(byte[][] intersections) {
        this.intersections = intersections;
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
                    if (intersections[i][wheel[j]] >= t) {
                        continue test2;
                    }
                }
                continue test;
            }
            System.out.println(b);
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
            b--;
//            return;
        }
    }
}