package utils;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static utils.Library.*;

public class BruteForceOnlySomeMSets {

    public static void main(String[] args) throws IOException {
        int mSetsCount = 612;
        int intersectionSize;
        int i, j, n, l, count = 0, randomNumber = 0;
        int[][] mSets = readFromFileNLines(mSetsCount, m);
        int[][] wheel = new int[b][k];
        List<Integer> usedNumbers = new ArrayList<>();
        long start = System.currentTimeMillis();
//        Collections.shuffle(Arrays.asList(kSets));
        Collections.shuffle(Arrays.asList(mSets));
        Random random = new Random();
        test:
        while (usedNumbers.size() != kSetsCount) {
//            count++;
////            System.out.println(count);
//            if (count == 30000) {
//                System.out.println(System.currentTimeMillis() - start);
//                start = System.currentTimeMillis();
//                count = 0;
//            }
            for (i = 0; i < b; i++) {
                randomNumber = random.nextInt(kSetsCount);
                wheel[i] = kSets[randomNumber];
                while (usedNumbers.contains(randomNumber)) {
                    randomNumber = random.nextInt(kSetsCount);
                    wheel[i] = kSets[randomNumber];
                }
            }
            test2:
            for (i = 0; i < mSetsCount; i++) {
                for (j = 0; j < b; j++) {
                    intersectionSize = 0;
                    for (n = 0; n < m; n++) {
                        for (l = 0; l < k; l++) {
                            if (mSets[i][n] == wheel[j][l]) {
                                intersectionSize++;
                                break;
                            }
                        }
                    }
                    if (intersectionSize >= t) {
                        continue test2;
                    }
                }
                continue test;
            }
            if (!usedNumbers.contains(randomNumber)) {
                usedNumbers.add(randomNumber);
//            System.out.println("A " + t + "-guaranteed wheel was found with a size = " + b);
                FileWriter fileWriter = new FileWriter("C:\\Users\\Stanislav Ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                fileWriter.write("A " + t + "-guaranteed wheel was found with a size = " + b + "\n");
                for (i = 0; i < b; i++) {
                    for (j = 0; j < k; j++) {
                        System.out.print(wheel[i][j] + " ");
                        fileWriter.append(wheel[i][j] + " ");
                    }
                    System.out.println();
                    fileWriter.append("\n");
                }
                System.out.println();
                fileWriter.append("\n");
                fileWriter.close();
//            return;
//            b--;
            }
        }
    }
}