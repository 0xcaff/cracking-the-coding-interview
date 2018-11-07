package Chapter16_6

import kotlin.math.abs
import kotlin.math.min

fun main(args: Array<String>) {
    require(smallestDifference(intArrayOf(1, 3, 15, 11, 2), intArrayOf(23, 127, 235, 19, 8)) == 3)
}

/**
 * Smallest Difference: Given two arrays of integers, compute the pair of
 * values (one value in each array) with the smallest (non-negative)
 * difference. Return the difference.
 *
 * EXAMPLE:
 * Input: {1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * Output: 3. That is, the pair (11, 8)
 */
fun smallestDifference(a: IntArray, b: IntArray): Int {
    var aIdx = 0
    a.sort()

    var bIdx = 0
    b.sort()

    var smallestDifference = Integer.MAX_VALUE
    while (aIdx < a.size && bIdx < b.size) {
        smallestDifference = min(smallestDifference, abs(a[aIdx] - b[bIdx]))

        if (a[aIdx] < b[bIdx]) {
            aIdx++
        } else {
            bIdx++
        }
    }

    return smallestDifference
}
