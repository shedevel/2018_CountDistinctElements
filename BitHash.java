import java.util.Scanner;

public class BitHash {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String out = "";
        String nl = "";

        while(sc.hasNext()) {
            int input = sc.nextInt();
            int hash = hash(input);

            out += nl + hash;
            nl = System.lineSeparator();
        }

        System.out.println(out);
    }

    private static int hash(int num) {
        int hash = 0;
        int[] appendix =
        {0x21ae4036, 0x32435171, 0xac3338cf, 0xea97b40c,
            0x0e504b22, 0x9ff9a4ef, 0x111d014d, 0x934f3787,
            0x6cd079bf, 0x69db5c31, 0xdf3c28ed, 0x40daf2ad,
            0x82a5891c, 0x4659c7b0, 0x73dc0ca8, 0xdad3aca2,
            0x00c74c7e, 0x9a2521e2, 0xf38eb6aa, 0x64711ab6,
            0x5823150a, 0xd13a3a9a, 0x30a5aa04, 0x0fb9a1da,
            0xef785119, 0xc9f0b067, 0x1e7dde42, 0xdda4a7b2,
            0x1a1c2640, 0x297c0633, 0x744edb48, 0x19adce93};


        for(int a : appendix) {
            // do bitwise AND on the two numbers
            int temp = a & num;
            // count the amount of 1's in the binary representation of the result
            int count = Long.bitCount(temp);
            // mod the count with 2 to get a 1 or 0
            int bit = count % 2;
            // move everything one bit to the left
            hash = hash << 1;
            // add the new bit
            hash += bit;
        }

        return hash;
    }
}
