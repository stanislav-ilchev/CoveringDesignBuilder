package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Toto12 {

    public static void main(String[] args) throws IOException {
        int matches, counter = 0;
        int[][] twelveSet = new int[1][12];
//        twelveSet[0] = new int[]{1, 2, 3, 4, 8, 11, 15, 23, 25, 31, 42, 44};
        int[][] sixSets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        int i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, j;
        for (i1 = 1; i1 <= 38; i1++) {
            for (i2 = i1 + 1; i2 <= 39; i2++) {
                for (i3 = i2 + 1; i3 <= 40; i3++) {
                    for (i4 = i3 + 1; i4 <= 41; i4++) {
                        for (i5 = i4 + 1; i5 <= 42; i5++) {
                            for (i6 = i5 + 1; i6 <= 43; i6++) {
                                for (i7 = i6 + 1; i7 <= 44; i7++) {
                                    for (i8 = i7 + 1; i8 <= 45; i8++) {
                                        for (i9 = i8 + 1; i9 <= 46; i9++) {
                                            for (i10 = i9 + 1; i10 <= 47; i10++) {
                                                for (i11 = i10 + 1; i11 <= 48; i11++) {
                                                    for (i12 = i11 + 1; i12 <= 49; i12++) {
                                                        counter++;
                                                        twelveSet[0][0] = i1;
                                                        twelveSet[0][1] = i2;
                                                        twelveSet[0][2] = i3;
                                                        twelveSet[0][3] = i4;
                                                        twelveSet[0][4] = i5;
                                                        twelveSet[0][5] = i6;
                                                        twelveSet[0][6] = i7;
                                                        twelveSet[0][7] = i8;
                                                        twelveSet[0][8] = i9;
                                                        twelveSet[0][9] = i10;
                                                        twelveSet[0][10] = i11;
                                                        twelveSet[0][11] = i12;
                                                        matches = 0;
                                                        for (j = 0; j < b; j++) {
                                                            if (intersection(twelveSet[0], sixSets[j]) == 6) {
                                                                matches++;
                                                            }
                                                        }
                                                        if (matches >= 4) {
                                                            System.out.println(Arrays.toString(twelveSet[0]) + " " + matches);
                                                            writeToFile(twelveSet);
                                                        }
                                                        if (counter % 1000000 == 0) {
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
    }
}
