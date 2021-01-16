package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.apache.commons.lang.StringUtils;

public class MinSetCover {

    public static boolean isUniverseCovered(short[][] subset) {
        int i, j, k;
        loop:
        for (i = 1; i <= 10; i++) {
            for (j = 0; j < subset.length; j++) {
                for (k = 0; k < subset[j].length; k++) {
                    if (i == subset[j][k]) {
                        continue loop;
                    }
                }
            }
            if (j == subset.length) {
                return false;
            }
        }
        return true;
    }


    public static int intersection(short[] list1, short[] list2) {
        int intersectionSize = 0;
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i] == list2[j]) {
                    intersectionSize++;
                    break;
                }
            }
        }
        return intersectionSize;
    }

    public static void main(String[] args) throws IOException {
        int ticketNumberLimit = 6;
        int subsetSize = 1;
        boolean readFile = true;
        int overlappingNumber, initialOverlappingNumber = 0, minOverlappingNumber;
        int i, j, m, count, randomNumber, randomNumber2;
        long iterations = ticketNumberLimit * subsetSize;
        System.out.println("Iterations: " + iterations);
        short[][] subsets = new short[ticketNumberLimit][];
        short[][] subsets2 = new short[subsetSize][];
        short[] changedTuple;
        List<Integer> usedNumbers = new ArrayList<>();
        BufferedReader br;
        Random random = new Random();
        String line;
        int row = 0;
        if (!readFile) {

        } else {
            br = new BufferedReader(new FileReader("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt"));
            try {
                line = br.readLine();
                count = StringUtils.countMatches(line, ",") + 1;
                while (line != null) {
                    subsets[row] = new short[count];
                    for (i = 0; i < count; i++) {
                        if (line.contains(",")) {
                            subsets[row][i] = Byte.parseByte(line.replaceAll("\\s", "").split(",")[i]);
                        } else {
                            subsets[row][i] = Byte.parseByte(line.replaceAll("\\s{2,}", " ").trim().replace(" ", ",").split(",")[i]);
                        }
                    }
                    line = br.readLine();
                    count = StringUtils.countMatches(line, ",") + 1;
                    row++;
                }
            } finally {
                br.close();
            }
        }
        count = 0;

        for (i = 0; i < subsetSize; i++) {
            m = random.nextInt(ticketNumberLimit);
            while (usedNumbers.contains(m)) {
                m = random.nextInt(ticketNumberLimit);
            }
            subsets2[i] = new short[subsets[m].length];
            for (j = 0; j < subsets[m].length; j++) {
                subsets2[i][j] = subsets[m][j];
            }
            usedNumbers.add(m);
        }
        for (i = 0; i < subsetSize; i++) {
            for (j = i + 1; j < subsetSize; j++) {
                initialOverlappingNumber += intersection(subsets2[i], subsets2[j]);
            }
        }
        minOverlappingNumber = initialOverlappingNumber + 1;
        test:
        while (!isUniverseCovered(subsets2)) {
            count++;
//            System.out.println(count);
            if (initialOverlappingNumber < minOverlappingNumber) {
                minOverlappingNumber = initialOverlappingNumber;
//                System.out.println(initialOverlappingNumber);
                FileWriter fileWriter = new FileWriter("C:\\Users\\stanislav.ilchev\\Desktop\\result.txt");
                fileWriter.flush();
                for (i = 0; i < subsetSize; i++) {
                    for (j = 0; j < subsets2[i].length; j++) {
                        fileWriter.append(subsets2[i][j] + " ");
                    }
                    fileWriter.append("\n");
                }
                fileWriter.close();
            }
            if (count == iterations) {
                System.out.println("Restarting...");
                subsetSize++;
                subsets2 = new short[subsetSize][];
                iterations = ticketNumberLimit * subsetSize;
                System.out.println("Iterations: " + iterations);
                count = 0;
                usedNumbers.clear();
                for (i = 0; i < subsetSize; i++) {
                    m = random.nextInt(ticketNumberLimit);
                    while (usedNumbers.contains(m)) {
                        m = random.nextInt(ticketNumberLimit);
                    }
                    subsets2[i] = new short[subsets[m].length];
                    for (j = 0; j < subsets[m].length; j++) {
                        subsets2[i][j] = subsets[m][j];
                    }
                    usedNumbers.add(m);
                }
                initialOverlappingNumber = 0;
                for (i = 0; i < subsetSize; i++) {
                    for (j = i + 1; j < subsetSize; j++) {
                        initialOverlappingNumber += intersection(subsets2[i], subsets2[j]);
                    }
                }
                minOverlappingNumber = initialOverlappingNumber + 1;
            }
            overlappingNumber = 0;
            randomNumber = random.nextInt(subsetSize);
            randomNumber2 = random.nextInt(ticketNumberLimit);
            changedTuple = new short[subsets2[randomNumber].length];
            for (i = 0; i < subsets2[randomNumber].length; i++) {
                changedTuple[i] = subsets2[randomNumber][i];
            }
            for (i = 0; i < subsets2[randomNumber].length; i++) {
                subsets2[randomNumber][i] = subsets[randomNumber2][i];
            }
            for (i = 0; i < subsetSize; i++) {
                for (j = i + 1; j < subsetSize; j++) {
                    overlappingNumber += intersection(subsets2[i], subsets2[j]);
                }
            }
            if (overlappingNumber < initialOverlappingNumber) {
                count = 0;
                initialOverlappingNumber = overlappingNumber;
            } else {
                for (i = 0; i < subsets2[randomNumber].length; i++) {
                    subsets2[randomNumber][i] = changedTuple[i];
                }
            }
        }
    }
}