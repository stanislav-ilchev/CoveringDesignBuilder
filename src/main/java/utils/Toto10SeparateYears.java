package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Toto10SeparateYears {

    public static void main(String[] args) throws IOException {
        long counter = 0;
        int[][] tenSet = new int[1][10];
        boolean isSetWinningIn2018, isSetWinningIn2019, isSetWinningIn2020, isSetWinningIn2021;
        int[][] sixSets2018 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2018.txt");
        int[][] sixSets2019 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2019.txt");
        int[][] sixSets2020 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2020.txt");
        int[][] sixSets2021 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2021.txt");
        int i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, j;
        for (i1 = 1; i1 <= 40; i1++) {
            for (i2 = i1 + 1; i2 <= 41; i2++) {
                for (i3 = i2 + 1; i3 <= 42; i3++) {
                    for (i4 = i3 + 1; i4 <= 43; i4++) {
                        for (i5 = i4 + 1; i5 <= 44; i5++) {
                            for (i6 = i5 + 1; i6 <= 45; i6++) {
                                for (i7 = i6 + 1; i7 <= 46; i7++) {
                                    for (i8 = i7 + 1; i8 <= 47; i8++) {
                                        for (i9 = i8 + 1; i9 <= 48; i9++) {
                                            for (i10 = i9 + 1; i10 <= 49; i10++) {
                                                counter++;
                                                if (counter % 1000000 == 0) {
                                                    System.out.println(counter);
                                                }
                                                isSetWinningIn2018 = false;
                                                isSetWinningIn2019 = false;
                                                isSetWinningIn2020 = false;
                                                isSetWinningIn2021 = false;
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
//                                                matches = 0;
                                                for (j = 0; j < b; j++) {
                                                    if (intersection(tenSet[0], sixSets2018[j]) == 6) {
                                                        isSetWinningIn2018 = true;
                                                        break;
                                                    }
                                                }
                                                if (!isSetWinningIn2018) {
                                                    continue;
                                                }
                                                for (j = 0; j < b; j++) {
                                                    if (intersection(tenSet[0], sixSets2019[j]) == 6) {
                                                        isSetWinningIn2019 = true;
                                                        break;
                                                    }
                                                }
                                                if (!isSetWinningIn2019) {
                                                    continue;
                                                }
                                                for (j = 0; j < b; j++) {
                                                    if (intersection(tenSet[0], sixSets2020[j]) == 6) {
                                                        isSetWinningIn2020 = true;
                                                        break;
                                                    }
                                                }
                                                if (!isSetWinningIn2020) {
                                                    continue;
                                                }
                                                for (j = 0; j < b; j++) {
                                                    if (intersection(tenSet[0], sixSets2021[j]) == 6) {
                                                        System.out.println(Arrays.toString(tenSet[0]));
                                                        writeToFile(tenSet);
                                                        break;
                                                    }
                                                }
                                                //matches++;
//                                                if (matches >= 2) {
//                                                    System.out.println(Arrays.toString(tenSet[0]) + " " + matches);
//                                                }
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