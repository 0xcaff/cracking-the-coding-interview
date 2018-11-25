/**
 * You are given an array of integers (both positive and negative). Find the
 * contiguous sequence with the largest product. Return the product.
 */
package Chapter16_17

fun main(args: Array<String>) {
    require(contiguousSequence(intArrayOf(1, 2, 3, 4, 5)) == 1 * 2 * 3 * 4 * 5)
    require(contiguousSequence(intArrayOf(1, 2, 3, -1, 4, 5)) == 4 * 5)
    require(contiguousSequence(intArrayOf(-1, 2, 3, -10)) == -1 * 2 * 3 * -10)
    require(contiguousSequence(intArrayOf(0, 1, 2, 0, 3, 0)) == 3)
    require(contiguousSequence(intArrayOf(0, 1, 2, 0, 3, 0).reversedArray()) == 3)
    require(contiguousSequence(intArrayOf(-5, 0, -4, 0, -3, 0, -2, 0, -1, 0)) == -1)
}

fun contiguousSequence(array: IntArray): Int {
    var largestPositiveEndingHere: Int? = null
    var largestNegativeEndingHere: Int? = null
    var maxSoFar: Int = Int.MIN_VALUE

    for (idx in 0..array.lastIndex) {
        val value = array[idx]

        when {
            value > 0 -> {
                if (largestPositiveEndingHere == null) {
                    largestPositiveEndingHere = value
                } else {
                    largestPositiveEndingHere *= value
                }

                if (largestNegativeEndingHere != null) {
                    largestNegativeEndingHere *= value
                }
            }
            value == 0 -> {
                largestNegativeEndingHere = null
                largestPositiveEndingHere = null
            }
            value < 0 -> {
                val temp = largestPositiveEndingHere

                largestPositiveEndingHere = if (largestNegativeEndingHere != null) {
                    largestNegativeEndingHere * value
                } else {
                    null
                }
                largestNegativeEndingHere = if (temp != null) {
                    temp * value
                } else {
                    value
                }
            }
        }

        if (largestPositiveEndingHere != null) {
            maxSoFar = Math.max(maxSoFar, largestPositiveEndingHere)
        }

        if (largestNegativeEndingHere != null) {
            maxSoFar = Math.max(maxSoFar, largestNegativeEndingHere)
        }
    }

    return maxSoFar
}
