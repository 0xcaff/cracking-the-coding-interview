package Chapter5_4;

// TODO: Add Tests

public class Main {
    private static boolean getBit(int num, int i) {
        return (num & (1 << i)) != 0;
    }

    private static int clearBit(int num, int i) {
        int mask = ~(1 << i);
        return num & mask;
    }

    private static int setBit(int num, int i) {
        return num | (1 << i);
    }

    private static int getNextSmallest(int num) {
        int leastSignificantZero = 0;
        int leastSignificantOneAfterZero = 0;


        for (int i = 1; i <= 32; i++) {
            boolean bit = getBit(num, i);
            if (leastSignificantZero == 0 && !bit) {
                leastSignificantZero = i;
            }

            if (leastSignificantZero != 0 && bit) {
                leastSignificantOneAfterZero = i;
                break;
            }
        }

        if (leastSignificantOneAfterZero == 0) {
            throw new IllegalArgumentException("Impossible");
        }

        return clearBit(setBit(num, leastSignificantZero), leastSignificantOneAfterZero);
    }

    private static int getNextLargest(int num) {
        int leastSignificantOne = 0;
        int leastSignificantZeroAfterOne = 0;


        for (int i = 1; i <= 32; i++) {
            boolean bit = getBit(num, i);
            if (leastSignificantOne == 0 && bit) {
                leastSignificantOne = i;
            }

            if (leastSignificantOne != 0 && !bit) {
                leastSignificantZeroAfterOne = i;
                break;
            }
        }

        if (leastSignificantZeroAfterOne == 0) {
            return num << 1;
        }

        return clearBit(setBit(num, leastSignificantZeroAfterOne), leastSignificantOne);
    }
}
