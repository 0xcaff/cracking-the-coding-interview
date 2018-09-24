package Chapter5_4;

// TODO: Tests Fail

public class Main {
    public static void main(String args[]) {
        testGetNext();
        testGetPrev();
    }

    private static void testGetNext() {
        int prev = 0b11011001111100;
        int next = 0b11011010001111;

        assert getNextLargest(prev) == next;
    }

    private static void testGetPrev() {
        int prev = 0b10011110000011;
        int next = 0b10011101110000;

        assert getNextSmallest(prev) == next;
    }

    private static int getNextSmallest(int num) {
        int temp = num;

        int c0 = 0;
        int c1 = 1;

        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        if (temp == 0) {
            throw new IllegalArgumentException("need to reduce the number of ones.");
        }

        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }

        int p = c0 + c1;

        num &= ((~0) << (p + 1));

        int mask = (1 << (c1 + 1)) - 1;
        num |= mask << (c0 - 1);

        return num;
    }

    private static int getNextLargest(int num) {
        int c = num;
        int trailingZeroes = 0;

        while (((c & 1) == 0 && (c != 0))) {
            trailingZeroes++;
            c >>= 1;
        }

        int onesAfterZero = 0;

        while ((c & 1) == 1) {
            onesAfterZero++;
            c >>= 1;
        }

        int rightmostNonTrailingZero = trailingZeroes + onesAfterZero;
        if (rightmostNonTrailingZero == 31 || rightmostNonTrailingZero == 0) {
            throw new IllegalArgumentException("No larger value.");
        }

        num |= (1 << rightmostNonTrailingZero);
        num &= ~((1 << rightmostNonTrailingZero) - 1);
        num |= (1 << (onesAfterZero - 1)) - 1;
        return num;
    }
}
