package Chapter5_3;

public class Main {
    public static void main(String[] args) {
        assert findLongestSequence(1775) == 8;
        assert findLongestSequence(0) == 0;
        assert findLongestSequence(-1) == Integer.BYTES * 8;
    }

    private static boolean getBit(int num, int i) {
        return (num & (1 << i)) != 0;
    }

    private static int findLongestSequence(int num) {
        int longestSequenceLength = 0;
        int lastSequenceLength = 0;
        int currentSequenceLength = 0;

        if (num == 0) {
            return 0;
        }

        if (num == -1) {
            return Integer.BYTES * 8;
        }

        for (int i = 0; i < 32; i++) {
            boolean bit = getBit(num, i);

            if (!bit) {
                int newSequenceLength = lastSequenceLength + currentSequenceLength + 1;
                if (newSequenceLength > longestSequenceLength) {
                    longestSequenceLength = newSequenceLength;
                }

                lastSequenceLength = currentSequenceLength;
                currentSequenceLength = 0;
            } else {
                currentSequenceLength++;
            }
        }

        return longestSequenceLength;
    }
}
