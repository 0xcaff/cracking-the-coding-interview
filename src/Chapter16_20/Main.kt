package Chapter16_20

fun main(args: Array<String>) {
    require(wordsForNumber(intArrayOf(8, 7, 3, 3)) == listOf("tree", "used"))
    require(wordsForNumber(intArrayOf(2, 3, 4)) == listOf<String>())
}

val charsForNumber = mapOf(
        Pair(1, arrayOf()),
        Pair(2, arrayOf('a', 'b', 'c')),
        Pair(3, arrayOf('d', 'e', 'f')),
        Pair(4, arrayOf('g', 'h', 'i')),
        Pair(5, arrayOf('j', 'k', 'l')),
        Pair(6, arrayOf('m', 'n', 'o')),
        Pair(7, arrayOf('p', 'q', 'r', 's')),
        Pair(8, arrayOf('t', 'u', 'v')),
        Pair(9, arrayOf('w', 'x', 'y', 'z')),
        Pair(0, arrayOf())
)

val wordlist = arrayOf("tree", "used", "car", "bus", "test")

val trie = run {
    val trie = TrieNode()
    trie.addAll(wordlist)
    trie
}

fun wordsForNumber(numbers: IntArray, root: TrieNode = trie): List<String> {
    if (numbers.isEmpty()) {
        return listOf()
    }

    val output = mutableListOf<String>()
    val charsFor = charsForNumber[numbers.first()]!!
    for (char in charsFor) {
        val nextRoot = root.getNextRoot(char)
        if (nextRoot != null) {
            if (numbers.size == 1 && nextRoot.isTerminal) {
                output += char.toString()
            } else {
                val numbersWithoutFirst = numbers.sliceArray(1..numbers.lastIndex)
                output += wordsForNumber(numbersWithoutFirst, nextRoot).map { char + it }
            }
        }
    }

    return output
}
