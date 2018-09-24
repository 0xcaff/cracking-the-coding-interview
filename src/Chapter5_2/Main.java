package Chapter5_2;

public class Main {
    public static void main(String[] args) {
        String actual = decimalToString(0.72);
    }

    private static String decimalToString(double dec) {
        if (dec < 0 || dec >= 1.0) {
            throw new IllegalArgumentException("bleah");
        }

        String output = "";
        while (dec > 0) {
            dec *= 2;

            int intPart = (int)dec;
            if (intPart >= 1) {
                output = "1" + output;
                dec -= 1;
            } else {
                output = "0" + output;
            }
        }

        return output;
    }
}
