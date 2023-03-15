package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Toto2 {

    public static void main(String[] args) throws IOException {
        int matches, counter = 0;
        int[][] twoSet = new int[1][2];
//        twoSet[0] = new int[]{1, 2, 3, 4, 8, 11, 15, 23, 25, 31, 42, 44};
        int[][] sixSets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        int i1, i2, j;
        for (i1 = 1; i1 <= 48; i1++) {
            for (i2 = i1 + 1; i2 <= 49; i2++) {
                counter++;
                twoSet[0][0] = i1;
                twoSet[0][1] = i2;
                matches = 0;
                for (j = 0; j < sixSets.length; j++) {
                    if (intersection(twoSet[0], sixSets[j]) == 2) {
                        matches++;
                    }
                }
                if (matches == 0) {
                    System.out.println(Arrays.toString(twoSet[0]) + " " + matches);
                    writeToFile(twoSet);
                }
//                                            if (counter % 10000000 == 0) {
//                                                System.out.println(counter);
//                                            }
            }
        }
    }
}


