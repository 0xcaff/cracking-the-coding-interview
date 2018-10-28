package Chapter10_2

import java.util.*

fun main(args: Array<String>) {
    println(sortLetters("god"))
    println(Arrays.toString(sortAnagrams(arrayOf(
            "cat",
            "dog",
            "god",
            "ogd",
            "odg",
            "racecar",
            "carrace"
    ))))
}

fun sortAnagrams(arr: Array<String>): Array<String>
    = arr
        .map { Pair(it, sortLetters(it)) }
        .sortedBy { it.second }
        .map { it.first }
        .toTypedArray()

fun sortLetters(string: String): String
    = string.asSequence().sorted().joinToString(separator = "")
