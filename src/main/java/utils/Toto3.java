package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Toto3 {

    public static void main(String[] args) throws IOException {
        int matches, counter = 0;
        int[][] threeSet = new int[1][3];
//        threeSet[0] = new int[]{1, 2, 3, 4, 8, 11, 15, 23, 25, 31, 42, 44};
        int[][] sixSets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        int i1, i2, i3, j;
        for (i1 = 1; i1 <= v - 2; i1++) {
            for (i2 = i1 + 1; i2 <= v - 1; i2++) {
                for (i3 = i2 + 1; i3 <= v; i3++) {
                    counter++;
                    threeSet[0][0] = i1;
                    threeSet[0][1] = i2;
                    threeSet[0][2] = i3;
                    matches = 0;
                    for (j = 0; j < sixSets.length; j++) {
                        if (intersection(threeSet[0], sixSets[j]) == 3) {
                            matches++;
                        }
                    }
                    if (matches >= 3) {
                        System.out.println(Arrays.toString(threeSet[0]) + " " + matches);
                        writeToFile(threeSet);
                    }
//                                            if (counter % 10000000 == 0) {
//                                                System.out.println(counter);
//                                            }
                }
            }
        }
    }
}


