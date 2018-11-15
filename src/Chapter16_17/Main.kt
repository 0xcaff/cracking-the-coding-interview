/**
 * You are given an array of integers (both positive and negative). Find the
 * contiguous sequence with the largest sum. Return the sum.
 */
package Chapter16_17

import kotlin.math.max

// Things to note:
// * a sequence never starts or ends with a negative number
// * all negative numbers at the beginning and end of an array can't be part of the largest sequence
// * never take zeros

// Ideas:
// * Brute Force
//    Time: O((n choose 2))
//    Space: O(1)
//
// * Divide and Conquer
//    Time: O(n)
//    Space: O(log n)
//
// * Dynamic Programming
//    Time: O(n)
//    Space: O(1)

fun main(args: Array<String>) {
    testBoth(intArrayOf(2, -8, 3, -2, 4, -10), 5)
    testBoth(intArrayOf(8, -6, -1, 3, -2, 4, 3, -10), 9)
    testBoth(intArrayOf(-4, -1, -3, -2), -1)
}

fun testBoth(array: IntArray, expected: Int) {
    require(contiguousSequenceDynamicProgramming(array) == expected)
    require(contiguousSequenceRecursive(array) == expected)
}

fun contiguousSequenceDynamicProgramming(array: IntArray): Int {
    var maxSoFar = array[0]
    var lastMaxEndingAt = array[0]

    for (idx in 1 until array.size) {
        val maxEndingAt = max(lastMaxEndingAt + array[idx], array[idx])
        maxSoFar = max(maxEndingAt, maxSoFar)
        lastMaxEndingAt = maxEndingAt
    }

    return maxSoFar
}

fun contiguousSequenceRecursive(array: IntArray): Int
    = (1..array.size)
        .map { size ->
            contiguousSequenceRecursiveHelper(array.sliceArray(0 until size))
        }
        .max()!!

fun contiguousSequenceRecursiveHelper(array: IntArray): Int {
    if (array.isEmpty()) {
        return 0
    }

    val withoutLast = array.sliceArray(0 until array.lastIndex)
    val bestSoFar = contiguousSequenceRecursiveHelper(withoutLast)
    return max(array.last(), bestSoFar + array.last())
}
