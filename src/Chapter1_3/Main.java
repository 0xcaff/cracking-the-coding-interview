package Chapter1_3;

public class Main {
    public static void main(String args[]) {
        assert check("Mr John Smith    ", 13, "Mr%20John%20Smith");
        assert check("Mr John Smith hello      ", 19, "Mr%20John%20Smith%20hello");
    }

    private static boolean check(String in, int trueLength, String out) {
        char[] chars = in.toCharArray();
        urlifyInPlace(chars, trueLength);
        return new String(chars).equals(out);
    }

    private static void urlifyInPlace(char chars[], int trueLength) {
        int spacesCount = countSpacesUpTo(chars, trueLength);
        int additionalSpacesCount = 2 * spacesCount;
        int lastIdx = trueLength - 1 + additionalSpacesCount;

        for (int outputIdx = lastIdx, inputIdx = trueLength - 1; outputIdx >= 0 && inputIdx >= 0; inputIdx--) {
            char inputChar = chars[inputIdx];
            if (inputChar == ' ') {
                chars[outputIdx] = '0';
                chars[outputIdx - 1] = '2';
                chars[outputIdx - 2] = '%';
                outputIdx -= 3;
            } else {
                chars[outputIdx] = inputChar;
                outputIdx -= 1;
            }
        }
    }

    private static int countSpacesUpTo(char chars[], int length) {
        int spaces = 0;

        for (int i = 0; i < length; i++) {
            char c = chars[i];

            if (c == ' ') {
                spaces++;
            }
        }

        return spaces;
    }
}
