package Chapter5_2;

public class Main {
    public static void main(String[] args) {
        assert decimalToString(0.6875).equals("1011");
    }

    private static String decimalToString(double dec) {
        if (dec < 0 || dec >= 1.0) {
            throw new IllegalArgumentException("bleah");
        }

        String output = "";
        while (dec > 0) {
            dec *= 2;

            if (output.length() > 32) {
                throw new IllegalArgumentException("too long");
            }

            int intPart = (int)dec;
            if (intPart >= 1) {
                output += "1";
                dec -= 1;
            } else {
                output += "0";
            }
        }

        return output;
    }
}
