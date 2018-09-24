package Chapter5_1;

public class Main {
    public static void main(String args[]) {
        assert reassignBits(0b10011, 0b10000000000, 2, 6) == 0b10001001100;
        assert reassignBits(0b10011, 0b11111111111, 2, 6) == 0b11111001111;
    }

    private static int reassignBits(int m, int n, int i, int j) {
        int len = j - i + 1;
        int maskedM = m & ~(-1 << len);

        int mask = (-1 << (j + 1)) ^ (-1 << i);

        int clearedN = n & ~mask;

        return clearedN | (maskedM << i);
    }
}
