package utils;

import java.io.IOException;

import static utils.Library.*;

public class Rename {

    public static void main(String[] args) throws IOException {
        int[][] kSets = readFromFile("C:\\Users\\Stanislav Ilchev\\Desktop\\input.txt");
        rename(kSets, -1);
//        renameFromTo(kSets, 22, 49);
        print(kSets);
    }
}