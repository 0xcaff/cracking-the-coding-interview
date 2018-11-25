/**
 * Sum Swap: Given two arrays of integers, find a pair of values (one from each
 * array) that you can swap to give the two arrays the same sum.
 */
package Chapter16_21

import java.lang.IllegalStateException

fun main(args: Array<String>) {
    require(sumSwap(intArrayOf(4, 1, 2, 1, 1, 2), intArrayOf(3, 6, 3, 3)) == Pair(4, 6))
    require(sumSwap(intArrayOf(1, 2, 3, 1), intArrayOf(3, 3, 1)) == Pair(1, 1))
    require(sumSwap(intArrayOf(10, 1), intArrayOf(7, 1)) == null)
}

/**
 * Assumes b.sum() > a.sum()
 */
fun sumSwap(a: IntArray, b: IntArray): Pair<Int, Int>? {
    val aSum = a.sum()
    val bSum = b.sum()

    // aSum - a + b = bSum - b + a
    // - a + b = bSum - aSum - b + a
    // -2a + 2b = bSum - aSum
    // 2(b - a) = bSum - aSum
    // b - a = (bSum - aSum)/2
    // b = (bSum - aSum) / 2 + a
    val difference = bSum - aSum
    if (difference % 2 != 0) {
        return null
    }

    val halfDifference = difference / 2

    val bSet = HashSet<Int>()
    bSet.addAll(b.toList())

    for (a in a) {
        val b = halfDifference + a
        if (bSet.contains(b)) {
            return Pair(a, b)
        }
    }

    throw IllegalStateException()
}

