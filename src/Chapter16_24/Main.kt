/**
 * Pairs with Sum: Design an algorithm to find all pairs of integers within an
 * array which sum to a specified value.
 */
package Chapter16_24

fun main(args: Array<String>) {
    require(pairsWithSum(intArrayOf(1, 2, 3), 3) == listOf(Pair(2, 1)))
}

fun pairsWithSum(array: IntArray, sum: Int): List<Pair<Int, Int>> {
    val output = mutableListOf<Pair<Int, Int>>()
    val sortedArray = array.sortedArray()

    for (idx in array.indices) {
        val value = array[idx]
        val lhs = sum - value
        val fromIdx = sortedArray.sliceArray(idx..sortedArray.lastIndex)

        if (fromIdx.binarySearch(lhs) != -1) {
            output += Pair(lhs, value)
        }
    }

    return output
}

