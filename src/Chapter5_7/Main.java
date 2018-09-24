package Chapter5_7;

public class Main {
    public static void main(String[] args) {
        assert pairwiseSwap(0b101010) == 0b010101;
        assert pairwiseSwap(0b111110) == 0b111101;
    }

    private static int pairwiseSwap(int num) {
        int evenMask = 0b10101010101010101010101010101010;
        int oddMask =  0b01010101010101010101010101010101;

        return (num & evenMask) >>> 1 | (num & oddMask) << 1;
    }
}
