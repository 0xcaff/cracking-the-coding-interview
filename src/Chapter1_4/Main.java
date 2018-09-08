package Chapter1_4;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String args[]) {
        assert isPermutationOfPalindrome("tactcoa");
        assert !isPermutationOfPalindrome("abcdefg");
        assert isPermutationOfPalindrome("aaaa");
        assert isPermutationOfPalindrome("aaaabbbb");
        assert !isPermutationOfPalindrome("aaaacbbb");
    }

    private static boolean isPermutationOfPalindrome(String string) {
        Set<Character> unmatchedCharacters = new HashSet<>();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (!unmatchedCharacters.contains(c)) {
                unmatchedCharacters.add(c);
            } else {
                unmatchedCharacters.remove(c);
            }
        }

        return unmatchedCharacters.size() <= 1;
    }
}
