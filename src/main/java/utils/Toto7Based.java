package utils;

import java.io.IOException;

import static utils.Library.intersection;
import static utils.Library.readFromFile;

public class Toto7Based {

    public static void main(String[] args) throws IOException {
        int matches = 0;
        int[][] elevenSet = new int[1][7];
        elevenSet[0] = new int[]{2, 7, 17, 21, 22, 30, 39};
        int[][] sixSets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        int i;
        for (i = 0; i < sixSets.length; i++) {
            if (intersection(elevenSet[0], sixSets[i]) == 4) {
                matches++;
            }
        }
        System.out.println(matches);
    }
}


