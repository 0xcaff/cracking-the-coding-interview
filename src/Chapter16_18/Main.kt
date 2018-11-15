package Chapter16_18

import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    testMatchesPattern()

    require(patternMatching("aabab", "catcatgocatgo"))
    require(patternMatching("a", "catcatgocatgo"))
    require(patternMatching("ab", "catcatgocatgo"))
    require(patternMatching("b", "catcatgocatgo"))
}

/**
 * The pattern string consists of just the letters a and b, describing a
 * pattern within a string.
 */
fun patternMatching(pattern: String, value: String): Boolean {
    if (pattern == "a" || pattern == "ab" || pattern == "b" || pattern == "ba") {
        return true
    }

    for (aLen in 1..value.length) {
        for (bLen in 1..value.length) {
            if (matchesPattern(pattern, value, aLen, bLen)) {
                return true
            }
        }
    }

    return false
}

fun testMatchesPattern() {
    require(matchesPattern("aabab", "catcatgocatgo", 3, 2))
    require(!matchesPattern("aabab", "catcatgocatgo", 4, 2))
    require(!matchesPattern("aabab", "catcatgocatgo", 2, 2))
}

// O(pattern)
fun matchesPattern(pattern: String, value: String, aLen: Int, bLen: Int): Boolean {
    var lastA: String? = null
    var lastB: String? = null
    var valueIdx = 0

    for (i in (0 until pattern.length)) {
        val char = pattern[i]

        val (lastOfKind, len) = when (char) {
            'a' -> Pair(lastA, aLen)
            'b' -> Pair(lastB, bLen)
            else -> throw IllegalArgumentException()
        }

        val currentOfKind = value.slice(valueIdx until (valueIdx + len))
        valueIdx += len

        when {
            lastOfKind == null -> {
                when (char) {
                    'a' -> lastA = currentOfKind
                    'b' -> lastB = currentOfKind
                }
            }
            lastOfKind != currentOfKind -> {
                return false
            }
        }
    }

    return true
}
