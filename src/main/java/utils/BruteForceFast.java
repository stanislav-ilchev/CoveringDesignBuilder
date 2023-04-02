package utils;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
            wheel[0] = random.nextInt(kSetsCount);
            wheel[1] = random.nextInt(kSetsCount);
            wheel[2] = random.nextInt(kSetsCount);
            wheel[3] = random.nextInt(kSetsCount);
            wheel[4] = random.nextInt(kSetsCount);
            wheel[5] = random.nextInt(kSetsCount);
            wheel[6] = random.nextInt(kSetsCount);
            wheel[7] = random.nextInt(kSetsCount);
            wheel[8] = random.nextInt(kSetsCount);
            wheel[9] = random.nextInt(kSetsCount);
            wheel[10] = random.nextInt(kSetsCount);
            wheel[11] = random.nextInt(kSetsCount);
            wheel[12] = random.nextInt(kSetsCount);
            wheel[13] = random.nextInt(kSetsCount);
            wheel[14] = random.nextInt(kSetsCount);
            wheel[15] = random.nextInt(kSetsCount);
            wheel[16] = random.nextInt(kSetsCount);
            wheel[17] = random.nextInt(kSetsCount);
            wheel[18] = random.nextInt(kSetsCount);
            wheel[19] = random.nextInt(kSetsCount);
            wheel[20] = random.nextInt(kSetsCount);
            wheel[21] = random.nextInt(kSetsCount);
            wheel[22] = random.nextInt(kSetsCount);
            wheel[23] = random.nextInt(kSetsCount);
            wheel[24] = random.nextInt(kSetsCount);
            wheel[25] = random.nextInt(kSetsCount);
            wheel[26] = random.nextInt(kSetsCount);
            wheel[27] = random.nextInt(kSetsCount);
            wheel[28] = random.nextInt(kSetsCount);
            wheel[29] = random.nextInt(kSetsCount);
            wheel[30] = random.nextInt(kSetsCount);
            wheel[31] = random.nextInt(kSetsCount);
            wheel[32] = random.nextInt(kSetsCount);
            wheel[33] = random.nextInt(kSetsCount);
            wheel[34] = random.nextInt(kSetsCount);
            wheel[35] = random.nextInt(kSetsCount);
            wheel[36] = random.nextInt(kSetsCount);
            wheel[37] = random.nextInt(kSetsCount);
            wheel[38] = random.nextInt(kSetsCount);
            wheel[39] = random.nextInt(kSetsCount);
            wheel[40] = random.nextInt(kSetsCount);
            wheel[41] = random.nextInt(kSetsCount);
            wheel[42] = random.nextInt(kSetsCount);
            wheel[43] = random.nextInt(kSetsCount);
            wheel[44] = random.nextInt(kSetsCount);
            wheel[45] = random.nextInt(kSetsCount);
            wheel[46] = random.nextInt(kSetsCount);
            wheel[47] = random.nextInt(kSetsCount);
            wheel[48] = random.nextInt(kSetsCount);
            wheel[49] = random.nextInt(kSetsCount);
            wheel[50] = random.nextInt(kSetsCount);
            wheel[51] = random.nextInt(kSetsCount);
            wheel[52] = random.nextInt(kSetsCount);
            wheel[53] = random.nextInt(kSetsCount);
            wheel[54] = random.nextInt(kSetsCount);
            wheel[55] = random.nextInt(kSetsCount);
            wheel[56] = random.nextInt(kSetsCount);
            wheel[57] = random.nextInt(kSetsCount);
            wheel[58] = random.nextInt(kSetsCount);
            wheel[59] = random.nextInt(kSetsCount);
            wheel[60] = random.nextInt(kSetsCount);
            wheel[61] = random.nextInt(kSetsCount);
            wheel[62] = random.nextInt(kSetsCount);
            wheel[63] = random.nextInt(kSetsCount);
            wheel[64] = random.nextInt(kSetsCount);
            wheel[65] = random.nextInt(kSetsCount);
            wheel[66] = random.nextInt(kSetsCount);
            wheel[67] = random.nextInt(kSetsCount);
            wheel[68] = random.nextInt(kSetsCount);
            wheel[69] = random.nextInt(kSetsCount);
            wheel[70] = random.nextInt(kSetsCount);
            wheel[71] = random.nextInt(kSetsCount);
            wheel[72] = random.nextInt(kSetsCount);
            wheel[73] = random.nextInt(kSetsCount);
            wheel[74] = random.nextInt(kSetsCount);
            wheel[75] = random.nextInt(kSetsCount);
            wheel[76] = random.nextInt(kSetsCount);
            wheel[77] = random.nextInt(kSetsCount);
            wheel[78] = random.nextInt(kSetsCount);
            wheel[79] = random.nextInt(kSetsCount);
            wheel[80] = random.nextInt(kSetsCount);
            wheel[81] = random.nextInt(kSetsCount);
            wheel[82] = random.nextInt(kSetsCount);
            wheel[83] = random.nextInt(kSetsCount);
            wheel[84] = random.nextInt(kSetsCount);
            wheel[85] = random.nextInt(kSetsCount);
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
            return;
//            b = b - 10;
        }
    }
}