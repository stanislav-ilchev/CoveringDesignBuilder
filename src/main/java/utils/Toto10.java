package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Toto10 {

    public static void main(String[] args) throws IOException {
        int matches, counter = 0;
        int[][] tenSet = new int[1][10];
//        tenSet[0] = new int[]{1, 2, 3, 4, 8, 11, 15, 23, 25, 31, 42, 44};
        int[][] sixSets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        int i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, j;
        for (i1 = 1; i1 <= v - 9; i1++) {
            for (i2 = i1 + 1; i2 <= v - 8; i2++) {
                for (i3 = i2 + 1; i3 <= v - 7; i3++) {
                    for (i4 = i3 + 1; i4 <= v - 6; i4++) {
                        for (i5 = i4 + 1; i5 <= v - 5; i5++) {
                            for (i6 = i5 + 1; i6 <= v - 4; i6++) {
                                for (i7 = i6 + 1; i7 <= v - 3; i7++) {
                                    for (i8 = i7 + 1; i8 <= v - 2; i8++) {
                                        for (i9 = i8 + 1; i9 <= v - 1; i9++) {
                                            for (i10 = i9 + 1; i10 <= v; i10++) {
                                                counter++;
                                                tenSet[0][0] = i1;
                                                tenSet[0][1] = i2;
                                                tenSet[0][2] = i3;
                                                tenSet[0][3] = i4;
                                                tenSet[0][4] = i5;
                                                tenSet[0][5] = i6;
                                                tenSet[0][6] = i7;
                                                tenSet[0][7] = i8;
                                                tenSet[0][8] = i9;
                                                tenSet[0][9] = i10;
                                                matches = 0;
                                                for (j = 0; j < b; j++) {
                                                    if (intersection(tenSet[0], sixSets[j]) >= 5) {
                                                        matches++;
                                                    }
                                                }
                                                if (matches >= 6) {
                                                    System.out.println(Arrays.toString(tenSet[0]) + " " + matches);
                                                    writeToFile(tenSet);
                                                }
                                                if (counter % 1000000000 == 0) {
                                                    System.out.println(counter);
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
        }
    }
}


