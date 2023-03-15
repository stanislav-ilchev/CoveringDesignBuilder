package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Toto9SeparateYears {

    public static void main(String[] args) throws IOException {
        long counter = 0;
        int[][] nineSet = new int[1][9];
        boolean isSetWinningIn2018, isSetWinningIn2019, isSetWinningIn2020, isSetWinningIn2021;
        int[][] sixSets2018 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2018.txt");
        int[][] sixSets2019 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2019.txt");
        int[][] sixSets2020 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2020.txt");
        int[][] sixSets2021 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2021.txt");
        int i1, i2, i3, i4, i5, i6, i7, i8, i9, j;
        for (i1 = 1; i1 <= 41; i1++) {
            for (i2 = i1 + 1; i2 <= 42; i2++) {
                for (i3 = i2 + 1; i3 <= 43; i3++) {
                    for (i4 = i3 + 1; i4 <= 44; i4++) {
                        for (i5 = i4 + 1; i5 <= 45; i5++) {
                            for (i6 = i5 + 1; i6 <= 46; i6++) {
                                for (i7 = i6 + 1; i7 <= 47; i7++) {
                                    for (i8 = i7 + 1; i8 <= 48; i8++) {
                                        for (i9 = i8 + 1; i9 <= 49; i9++) {
//                                            counter++;
//                                            if (counter % 1000000 == 0) {
//                                                System.out.println(counter);
//                                            }
                                            isSetWinningIn2018 = false;
                                            isSetWinningIn2019 = false;
                                            isSetWinningIn2020 = false;
                                            isSetWinningIn2021 = false;
                                            nineSet[0][0] = i1;
                                            nineSet[0][1] = i2;
                                            nineSet[0][2] = i3;
                                            nineSet[0][3] = i4;
                                            nineSet[0][4] = i5;
                                            nineSet[0][5] = i6;
                                            nineSet[0][6] = i7;
                                            nineSet[0][7] = i8;
                                            nineSet[0][8] = i9;
//                                                matches = 0;
                                            for (j = 0; j < b; j++) {
                                                if (intersection(nineSet[0], sixSets2018[j]) == 6) {
                                                    isSetWinningIn2018 = true;
                                                    break;
                                                }
                                            }
                                            if (!isSetWinningIn2018) {
                                                continue;
                                            }
                                            for (j = 0; j < b; j++) {
                                                if (intersection(nineSet[0], sixSets2019[j]) == 6) {
                                                    isSetWinningIn2019 = true;
                                                    break;
                                                }
                                            }
                                            if (!isSetWinningIn2019) {
                                                continue;
                                            }
                                            for (j = 0; j < b; j++) {
                                                if (intersection(nineSet[0], sixSets2020[j]) == 6) {
                                                    isSetWinningIn2020 = true;
                                                    break;
                                                }
                                            }
                                            if (!isSetWinningIn2020) {
                                                continue;
                                            }
                                            for (j = 0; j < b; j++) {
                                                if (intersection(nineSet[0], sixSets2021[j]) == 6) {
                                                    System.out.println(Arrays.toString(nineSet[0]));
                                                    writeToFile(nineSet);
                                                    break;
                                                }
                                            }
                                            //matches++;
//                                                if (matches >= 2) {
//                                                    System.out.println(Arrays.toString(nineSet[0]) + " " + matches);
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