package Chapter1_6;

public class Main {
    public static void main(String args[]) {
        assert compressedIfBetter("aabbcc").equals("aabbcc");
        assert compressedIfBetter("aaabbbccc").equals("a3b3c3");
        assert compressedIfBetter("aabbcccccd").equals("a2b2c5d1");
    }

    private static String compressedIfBetter(String in) {
        String encoded = compress(in);
        if (encoded.length() < in.length()) {
            return encoded;
        }

        return in;
    }

    private static String compress(String in) {
        StringBuilder builder = new StringBuilder();
        LastCharInformation last = null;

        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);

            if (last != null) {
                if (last.c == c) {
                    last.incr();
                } else {
                    builder.append(last.encode());
                    last = null;
                }
            }

            if (last == null) {
                last = new LastCharInformation(c);
            }
        }

        if (last != null) {
            builder.append(last.encode());
        }

        return builder.toString();
    }

    public static class LastCharInformation {
        private int count = 1;
        final char c;

        LastCharInformation(char c) {
            this.c = c;
        }

        void incr() {
            count++;
        }

        String encode() {
            return "" + c + count;
        }
    }
}
