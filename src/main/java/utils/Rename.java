package utils;

import java.io.IOException;

import static utils.Library.*;

public class Rename {

    public static void main(String[] args) throws IOException {
        int[][] kSets = readFromFile("C:\\Users\\stanislav.ilchev\\Desktop\\input.txt");
        rename(kSets, -22);
        print(kSets);
    }
}