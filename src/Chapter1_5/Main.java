package Chapter1_5;

public class Main {
    public static void main(String args[]) {
        assert isOneEditAway("pale", "ple");
        assert isOneEditAway("pales", "pale");
        assert isOneEditAway("pale", "bale");
        assert !isOneEditAway("pale", "bake");
        assert !isOneEditAway("abcdefg", "gfedcba");
        assert !isOneEditAway("paramedic", "parachutemedic");
        assert isOneEditAway("test", "tesa");
        assert isOneEditAway("test", "tes");
        assert !isOneEditAway("testing", "tes");
        assert isOneEditAway("hello", "hello");
    }

    private static boolean isOneEditAway(String a, String b) {
        int edits = 0;

        String shorter = getShorter(a, b);
        String longer = getLonger(a, b);

        for (int shorterIdx = 0, longerIdx = 0; longerIdx < longer.length(); ) {
            Character fromShorter = getCharacter(shorter, shorterIdx);
            Character fromLonger = getCharacter(longer, longerIdx);

            if (isEqual(fromShorter, fromLonger)) {
                shorterIdx++;
                longerIdx++;

                continue;
            }

            Character nextLongerCharacter = getCharacter(longer, longerIdx + 1);
            Character nextShorterCharacter = getCharacter(shorter, shorterIdx + 1);
            if (isEqual(nextLongerCharacter, fromShorter)) {
                // insertion magnitude 1 from shorter string to longer
                edits++;
                longerIdx++;
            } else if (isEqual(nextLongerCharacter, nextShorterCharacter)) {
                // replacement of magnitude 1.
                edits++;
            } else {
                return false;
            }

            if (edits >= 2) {
                return false;
            }

            shorterIdx++;
            longerIdx++;
        }

        return true;
    }

    private static String getShorter(String a, String b) {
        if (a.length() > b.length()) {
            return b;
        }

        return a;
    }

    private static String getLonger(String a, String b) {
        if (a.length() > b.length()) {
            return a;
        }

        return b;
    }

    private static Character getCharacter(String s, int idx) {
        if (idx < 0 || idx >= s.length()) {
            return null;
        }

        return s.charAt(idx);
    }

    private static boolean isEqual(Character a, Character b) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return a.equals(b);
    }
}
