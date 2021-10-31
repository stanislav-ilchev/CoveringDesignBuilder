package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Randomizer {

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        int i;
        String[] skus = new String[100];
        String line;
        int row = 0;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Stanislav Ilchev\\Desktop\\sku-s.txt"));
        try {
            line = br.readLine();
            while (line != null) {
                skus[row] = line;
                line = br.readLine();
                row++;
            }
        } finally {
            br.close();
        }
        for (i = 0; i < 100; i++) {
            System.out.println(skus[random.nextInt(100)] + "," + skus[random.nextInt(100)] + "," + skus[random.nextInt(100)] + "," + skus[random.nextInt(100)]);
        }

    }
}
