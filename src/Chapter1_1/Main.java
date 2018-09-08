package Chapter1_1;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String args[]) {
        assert(uniqueCharacters("abc"));
        assert(!uniqueCharacters("ccc"));
    }

    private static boolean uniqueCharacters(String string) {
        Set<Character> characters = new HashSet<>();

        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);

            if (characters.contains(character)) {
                return false;
            }

            characters.add(character);
        }

        return true;
    }
}
