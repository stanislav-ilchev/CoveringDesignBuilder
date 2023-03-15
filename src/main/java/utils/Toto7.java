package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Toto7 {

    public static void main(String[] args) throws IOException {
        int matches, counter = 0;
        int[][] sevenSet = new int[1][7];
//        sevenSet[0] = new int[]{1, 2, 3, 4, 8, 11, 15, 23, 25, 31, 42, 44};
        int[][] sixSets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        int i1, i2, i3, i4, i5, i6, i7, j;
        for (i1 = 1; i1 <= v - 6; i1++) {
            for (i2 = i1 + 1; i2 <= v - 5; i2++) {
                for (i3 = i2 + 1; i3 <= v - 4; i3++) {
                    for (i4 = i3 + 1; i4 <= v - 3; i4++) {
                        for (i5 = i4 + 1; i5 <= v - 2; i5++) {
                            for (i6 = i5 + 1; i6 <= v - 1; i6++) {
                                for (i7 = i6 + 1; i7 <= v; i7++) {
                                    counter++;
                                    sevenSet[0][0] = i1;
                                    sevenSet[0][1] = i2;
                                    sevenSet[0][2] = i3;
                                    sevenSet[0][3] = i4;
                                    sevenSet[0][4] = i5;
                                    sevenSet[0][5] = i6;
                                    sevenSet[0][6] = i7;
                                    matches = 0;
                                    for (j = 0; j < b; j++) {
                                        if (intersection(sevenSet[0], sixSets[j]) >= 3) {
                                            matches++;
                                        }
                                    }
                                    if (matches >= 14) {
                                        System.out.println(Arrays.toString(sevenSet[0]) + " " + matches);
                                        writeToFile(sevenSet);
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