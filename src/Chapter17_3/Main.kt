/**
 * Random Set: Write a method to randomly generate a set of m integers from an
 * array of size n. Each element must have an equal probability of being
 * chosen.
 */
package Chapter17_3

import java.util.*

fun main(args: Array<String>) {
    val s = intArrayOf(1, 2, 3, 4, 5)
    val r = randomSet(s, 3)
    println(r.toList())

    require(r.size == 3)
}

/*
Ideas:
The number of ways to choose a set of m from a set of n (n choose m) or n!/(m! (n - m)!)

(n choose k) = ((n - 1) choose (k - 1)) + ((n - 1) choose k)
 */
fun randomSet(ints: IntArray, m: Int): IntArray {
    if (m == ints.size) {
        return ints
    }

    if (m == 0) {
        return intArrayOf()
    }

    // Choose one special element.
    val specialIdx = (0..ints.lastIndex).random()
    val temp = ints.toMutableList()
    temp.removeAt(specialIdx)
    val withoutSpecial = temp.toIntArray()

    return when {
        maybe() -> randomSet(withoutSpecial, m)
        else -> intArrayOf(ints[specialIdx]) + randomSet(withoutSpecial, m - 1)
    }
}

fun IntRange.random() =
    Random().nextInt((endInclusive + 1) - start) +  start

fun maybe() =
    Random().nextBoolean()

