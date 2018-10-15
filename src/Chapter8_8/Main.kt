package Chapter8_8

fun main(args: Array<String>) {
    require(permutationWithDups("aaaa") == listOf("aaaa"))
    require(permutationWithDups("aba") == listOf("aab", "aba", "baa"))
    require(permutationWithDups("aabb") == listOf("aabb", "baab", "bbaa", "baba", "abab", "abba"))
}

fun permutationWithDups(string: String): List<String> {
    val counts = countsOf(string)
    return permutationsFromCounts(counts)
}

fun permutationsFromCounts(counts: Map<Char, Int>): List<String> {
    if (counts.isEmpty()) {
        return listOf("")
    }

    val output = mutableListOf<String>()
    for (pair in counts) {
        val next = counts.toMutableMap()
        next.compute(pair.key) { _, value ->
            if (value == 1) {
                null
            } else {
                value!! - 1
            }
        }

        output.addAll(permutationsFromCounts(next).map { s -> pair.key + s })
    }

    return output
}

fun countsOf(string: String): HashMap<Char, Int> {
    val counts = hashMapOf<Char, Int>()

    for (char in string) {
        val prevCount = counts.getOrDefault(char, 0)
        counts[char] = prevCount + 1
    }

    return counts
}
