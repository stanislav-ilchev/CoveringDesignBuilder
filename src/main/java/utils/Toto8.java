package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Toto8 {

    public static void main(String[] args) throws IOException {
        int matches, counter = 0;
        int[][] eightSet = new int[1][8];
//        eightSet[0] = new int[]{1, 2, 3, 4, 8, 11, 15, 23, 25, 31, 42, 44};
        int[][] sixSets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        int i1, i2, i3, i4, i5, i6, i7, i8, j;
        for (i1 = 1; i1 <= v - 7; i1++) {
            for (i2 = i1 + 1; i2 <= v - 6; i2++) {
                for (i3 = i2 + 1; i3 <= v - 5; i3++) {
                    for (i4 = i3 + 1; i4 <= v - 4; i4++) {
                        for (i5 = i4 + 1; i5 <= v - 3; i5++) {
                            for (i6 = i5 + 1; i6 <= v - 2; i6++) {
                                for (i7 = i6 + 1; i7 <= v - 1; i7++) {
                                    for (i8 = i7 + 1; i8 <= v; i8++) {
                                        counter++;
                                        eightSet[0][0] = i1;
                                        eightSet[0][1] = i2;
                                        eightSet[0][2] = i3;
                                        eightSet[0][3] = i4;
                                        eightSet[0][4] = i5;
                                        eightSet[0][5] = i6;
                                        eightSet[0][6] = i7;
                                        eightSet[0][7] = i8;
                                        matches = 0;
                                        for (j = 0; j < sixSets.length; j++) {
                                            if (intersection(eightSet[0], sixSets[j]) >= 5) {
                                                matches++;
                                            }
                                        }
                                        if (matches >= 4) {
                                            System.out.println(Arrays.toString(eightSet[0]) + " " + matches);
                                            writeToFile(eightSet);
                                        }
//                                            if (counter % 10000000 == 0) {
//                                                System.out.println(counter);
//                                            }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


