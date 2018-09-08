package Chapter1_2;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]) {
        assert isPermutationOf("abc", "cab");
        assert isPermutationOf("abc", "abc");
        assert !isPermutationOf("mark", "martin");
    }

    private static boolean isPermutationOf(String a, String b) {
        Map<Character, Integer> counts = countChars(a);
        return hasSameCounts(counts, b);
    }

    private static Map<Character, Integer> countChars(String s) {
        Map<Character, Integer> counts = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int oldCount = counts.getOrDefault(c, 0);
            int newCount = oldCount + 1;

            counts.put(c, newCount);
        }

        return counts;
    }

    private static boolean hasSameCounts(Map<Character, Integer> otherCounts, String s) {
        Map<Character, Integer> counts = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int oldCount = counts.getOrDefault(c, 0);
            int newCount = oldCount + 1;

            int otherCount = otherCounts.getOrDefault(c, 0);
            if (newCount > otherCount) {
                return false;
            }

            counts.put(c, newCount);
        }

        return true;
    }
}
