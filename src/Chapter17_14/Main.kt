package Chapter17_14

/*
Ideas:
  * Sort and Select
        Time: O(n log n)
        Space: O(log n) to O(n)

  * Get List of Smallest
        Time: O(n^2)
        Space: O(n)

  * Quick Selection Idea
        Time: O(n) to O(n^2)
        Space: O(1)
 */

fun main(args: Array<String>) {
    require(smallestK(intArrayOf(1, 7, 4, 6, 2, 1), 2).toSet() == setOf(1, 1))
    require(smallestK(intArrayOf(1, 7, 4, 6, 2, 1), 3).toSet() == setOf(1, 1, 2))
    require(smallestK(intArrayOf(1, 7, 4, 6, 2, 1), 4).toSet() == setOf(1, 2, 1, 4))
    require(smallestK(intArrayOf(1, 7, 4, 6, 2, 1), 5).toSet() == setOf(1, 2, 1, 4, 6))
    require(smallestK(intArrayOf(1, 7, 4, 6, 2, 1), 6).toSet() == setOf(1, 4, 6, 2, 1, 7))
    require(smallestK(intArrayOf(1, 7, 4, 6, 2, 1), 7).toSet() == setOf(1, 2, 1, 4, 6, 7))
}

/**
 * Design an algorithm to find the smallest k numbers in an array.
 */
fun smallestK(arr: IntArray, k: Int): IntArray {
    if (k >= arr.size) {
        return arr
    }

    val (partitions, pivot) = partition(arr)
    val (less, greater) = partitions

    return when {
        less.size == k - 1 -> less + pivot
        less.size > k - 1 -> smallestK(less, k)
        else /* less.size < k - 1 */ -> less + pivot + smallestK(greater, k - less.size - 1)
    }
}

fun partition(arr: IntArray): Pair<Pair<IntArray, IntArray>, Int> {
    val pivot = arr[0]
    val lessThan = mutableListOf<Int>()
    val greaterThan = mutableListOf<Int>()

    for (value in arr.sliceArray(1..arr.lastIndex)) {
        when {
            value < pivot -> lessThan.add(value)
            value >= pivot -> greaterThan.add(value)
        }
    }

    return Pair(Pair(lessThan.toIntArray(), greaterThan.toIntArray()), pivot)
}
