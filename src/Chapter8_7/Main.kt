package Chapter8_7

fun main(args: Array<String>) {
    require(permutationsWithoutDups("a") == listOf("a"))
    require(permutationsWithoutDups("ab") == listOf("ab", "ba"))
    require(permutationsWithoutDups("abc") == listOf("abc", "acb", "bac", "bca", "cab", "cba"))
    require(permutationsWithoutDups("abcd").size == 24)
}

fun permutationsWithoutDups(remaining: String): List<String> {
    if (remaining.isEmpty()) {
        return listOf("")
    }

    val list = mutableListOf<String>()
    for (i in 0 until remaining.length) {
        val special = remaining[i]
        val withoutSpecial = remaining.withoutIdx(i)

        for (permutation in permutationsWithoutDups(withoutSpecial)) {
            list.add(special + permutation)
        }
    }

    return list
}

fun String.withoutIdx(i: Int): String
    = this.slice(0 until i) + this.slice(i + 1 until this.length)
