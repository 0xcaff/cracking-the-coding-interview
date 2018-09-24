package Chapter5_6;

public class Main {
    public static void main(String []args) {
        assert flipDistance(29, 15) == 2;
    }

    private static boolean getBit(int num, int i) {
        return (num & (1 << i)) != 0;
    }

    private static int flipDistance(int a, int b) {
        int distance = 0;

        for (int i = 1; i <= 32; i++) {
            boolean aBit = getBit(a, i);
            boolean bBit = getBit(b, i);

            if (aBit != bBit) {
                distance++;
            }
        }

        return distance;
    }
}
