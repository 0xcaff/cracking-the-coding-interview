package Chapter17_15

import Chapter16_20.TrieNode
import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    require(longestWord(
            arrayOf("cat", "banana", "dog", "nana", "walk", "walker", "dogwalker")
    ) == "dogwalker")
}

/*
Ideas:
    * Brute Force
        Try all combinations and return the longest.
        Combinations: 2^n n!

    * Alternative Brute Force
        Instead of trying all combinations, try to split each word and check
        whether the parts are in the word list.

        Problem: We don't know how many words to split each word into.

        We could try to take the first few characters until we find a match
        and keep trying until longest match.

        Number of Ways to Chop Up String of Size n:
        (n choose n) + (n choose (n - 1)) + (n choose (n - 2)) + ... = 2^n

    * Repeated Subproblems
        Repeated Substrings
        Prefix Matching
 */

/**
 * Given a list of words, find the longest word made of other words
 * in the list.
 */
fun longestWord(words: Array<String>): String {
    val trie = TrieNode()
    trie.addAll(words)

    words.sortBy { s -> -s.length }

    for (word in words) {
        if (wordMadeFromWordsInTrie(word, trie)) {
            return word
        }
    }

    throw IllegalArgumentException()
}

/*
This could be optimized further by expanding the trie a bit when we find something below a node.
 */
fun wordMadeFromWordsInTrie(
        word: String,
        root: TrieNode,
        node: TrieNode = root,
        wordsUsedBefore: Int = 0
): Boolean {
    if (word.isEmpty()) {
        return wordsUsedBefore > 0 && node.isTerminal
    }

    val nextNode = node.getNextRoot(word[0]) ?: return false
    val nextWord = word.slice(1..word.lastIndex)

    return when {
        wordMadeFromWordsInTrie(
                nextWord, root, nextNode, wordsUsedBefore) -> true
        else ->
            nextNode.isTerminal &&
                    wordMadeFromWordsInTrie(nextWord, root, root, wordsUsedBefore + 1)
    }
}
