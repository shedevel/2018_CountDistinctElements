import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Author: Sabina Hult
 * Implementation from pseudo-code of the HyperLogLog algorithm including the
 * two hash-functions.
 */
public class DistinctElements {
    public static void main(String[] args) {
        // given in the assignment
        final int[] matrix = { 0x21ae4036, 0x32435171, 0xac3338cf, 0xea97b40c, 0x0e504b22, 0x9ff9a4ef, 0x111d014d, 0x934f3787,
                0x6cd079bf, 0x69db5c31, 0xdf3c28ed, 0x40daf2ad, 0x82a5891c, 0x4659c7b0, 0x73dc0ca8, 0xdad3aca2,
                0x00c74c7e, 0x9a2521e2, 0xf38eb6aa, 0x64711ab6, 0x5823150a, 0xd13a3a9a, 0x30a5aa04, 0x0fb9a1da,
                0xef785119, 0xc9f0b067, 0x1e7dde42, 0xdda4a7b2, 0x1a1c2640, 0x297c0633, 0x744edb48, 0x19adce93 };

        Scanner sc = new Scanner(System.in);

        int threshold = sc.nextInt();
        // given in the assignment
        int m = 1024;
        int[] M = new int[m];

        while (sc.hasNext()) {
            int y = sc.nextInt();
            int j = f(y);

            // number of leading 0's + 1
            int next = Integer.numberOfLeadingZeros(h(y, matrix)) + 1;
            if (M[j] < next) M[j] = next;
        }

        sc.close();

        // summing up 2^-M[0], ... , 2^-M[m-1]
        double sum = 0;
        int V = 0;
        for (int i = 0; i < M.length; i++) {
            sum += Math.pow(2.0, -M[i]);
            if (M[i] == 0) V++;
        }

        // harmonic mean
        double Z = 1.0 / sum;

        // distinct elements estimate calculation
        double E = m * m * Z * 0.7213 / (1 + 1.079 / m);

        double x = m / (double) V;
        if ((E < (2.5 * m)) && (V > 0))
            E = m * Math.log(x);

        // compare to threshold and output if estimate is above or below
        if (E < threshold)
            System.out.println("below");
        else
            System.out.println("above");
    }

    public static int f(int y) {
        return ((y * 0xbc164501) & 0x7fe00000) >> 21;
    }

    public static int h(int num) {
        int hash = 0;

        for (int a : matrix) {
            int temp = a & num;
            hash = hash << 1;
            hash += Long.bitCount(temp) % 2;
        }

        return hash;
    }
}
