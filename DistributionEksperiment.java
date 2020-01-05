import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
* Author: Sabina Hult
*/

public class DistributionEksperiment {
    public static void main(String[] args) {
        // number of elements
        int n = 1000000;
        // upper bound for range
        int b = 1000000;
        // number of buckets
        int buckets = 100;

        String filename = "data/input1MioPos.txt";
        //createTextFileWithRandomNumbers(n, b, filename);

        BitHash bh = new BitHash();
        int[] hashCounts = new int[buckets];

        try {
            Scanner sc = new Scanner(new File(filename));

            while(sc.hasNext()) {
                int next = sc.nextInt();
                //System.out.print(next);
                int hash = bh.getHash(next);
                int i = hash % buckets;
                //System.out.println(" | Hashed to: " + hash + " | Mod to index : " + i);
                hashCounts[i]++;
                //System.out.println(" | Hashed to: " + hash);
            }

            for(int j = 0; j < hashCounts.length; j++) {
                double p = hashCounts[j] / 10000.0;
                System.out.println(String.format("%d : %d --> %.2f percent", j, hashCounts[j], p));
            }


        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static boolean createTextFileWithRandomNumbers(int n, int upperBound, String filepath) {
        try {
            PrintWriter out = new PrintWriter(new File(filepath));
            Random r = new Random(42);

            while(n > 0) {
                int k = r.nextInt(upperBound);
                out.println(k);
                n--;
            }

            return true;
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        }
    }
}
