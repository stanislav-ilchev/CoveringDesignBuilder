package utils;

import java.io.IOException;
import java.util.Arrays;

import static utils.Library.*;

public class Toto11SeparateYears {

    public static void main(String[] args) throws IOException {
        long counter = 0;
        int[][] elevenSet = new int[1][11];
        boolean isSetWinningIn2018, isSetWinningIn2019, isSetWinningIn2020, isSetWinningIn2021;
        int[][] sixSets2018 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2018.txt");
        int[][] sixSets2019 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2019.txt");
        int[][] sixSets2020 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2020.txt");
        int[][] sixSets2021 = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\toto_2021.txt");
        int i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, j;
        for (i1 = 1; i1 <= 39; i1++) {
            for (i2 = i1 + 1; i2 <= 40; i2++) {
                for (i3 = i2 + 1; i3 <= 41; i3++) {
                    for (i4 = i3 + 1; i4 <= 42; i4++) {
                        for (i5 = i4 + 1; i5 <= 43; i5++) {
                            for (i6 = i5 + 1; i6 <= 44; i6++) {
                                for (i7 = i6 + 1; i7 <= 45; i7++) {
                                    for (i8 = i7 + 1; i8 <= 46; i8++) {
                                        for (i9 = i8 + 1; i9 <= 47; i9++) {
                                            for (i10 = i9 + 1; i10 <= 48; i10++) {
                                                for (i11 = i10 + 1; i11 <= 49; i11++) {
                                                    counter++;
                                                    if (counter % 1000000 == 0) {
                                                        System.out.println(counter);
                                                    }
                                                    isSetWinningIn2018 = false;
                                                    isSetWinningIn2019 = false;
                                                    isSetWinningIn2020 = false;
                                                    isSetWinningIn2021 = false;
                                                    elevenSet[0][0] = i1;
                                                    elevenSet[0][1] = i2;
                                                    elevenSet[0][2] = i3;
                                                    elevenSet[0][3] = i4;
                                                    elevenSet[0][4] = i5;
                                                    elevenSet[0][5] = i6;
                                                    elevenSet[0][6] = i7;
                                                    elevenSet[0][7] = i8;
                                                    elevenSet[0][8] = i9;
                                                    elevenSet[0][9] = i10;
                                                    elevenSet[0][10] = i11;
//                                                matches = 0;
                                                    for (j = 0; j < b; j++) {
                                                        if (intersection(elevenSet[0], sixSets2018[j]) == 6) {
                                                            isSetWinningIn2018 = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!isSetWinningIn2018) {
                                                        continue;
                                                    }
                                                    for (j = 0; j < b; j++) {
                                                        if (intersection(elevenSet[0], sixSets2019[j]) == 6) {
                                                            isSetWinningIn2019 = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!isSetWinningIn2019) {
                                                        continue;
                                                    }
                                                    for (j = 0; j < b; j++) {
                                                        if (intersection(elevenSet[0], sixSets2020[j]) == 6) {
                                                            isSetWinningIn2020 = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!isSetWinningIn2020) {
                                                        continue;
                                                    }
                                                    for (j = 0; j < b; j++) {
                                                        if (intersection(elevenSet[0], sixSets2021[j]) == 6) {
                                                            System.out.println(Arrays.toString(elevenSet[0]));
                                                            writeToFile(elevenSet);
                                                            break;
                                                        }
                                                    }
                                                    //matches++;
//                                                if (matches >= 2) {
//                                                    System.out.println(Arrays.toString(elevenSet[0]) + " " + matches);
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
}