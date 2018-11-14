package Chapter16_16

fun main(args: Array<String>) {
    require(subsort(intArrayOf(1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19)) == Pair(3, 9))
    //                                  ----------------------

    require(subsort(intArrayOf(1, 2, 4, 7, 10, 11, 7, 12, 6, 7)) == Pair(3, 9))
    //                                  ----------------------

    require(subsort(intArrayOf(7, 10, 11, 7, 12, 6, 7, 16, 18, 19)) == Pair(0, 6))
    //                         ----------------------

    require(subsort(intArrayOf(10, 12, 20, 30, 25, 40, 32, 31, 35, 50)) == Pair(3, 8))
    //                                     ----------------------

    require(subsort(intArrayOf(0, 1, 15, 25, 6, 7, 30, 40, 50)) == Pair(2, 5))
    //                               ------------
}

/**
 * Given an array of integers, write a method to find indices m and n such that
 * if you sorted elements m through n, the entire array would be sorted.
 * Minimize n - m (that is, find the smallest such sequence).
 */
fun subsort(array: IntArray): Pair<Int, Int> {
    var max = array[0]
    var end = -1

    for (idx in 1 until array.size) {
        if (array[idx] >= max) {
            max = array[idx]
        } else {
            end = idx
        }
    }

    var start = -1
    var min = array[array.size - 1]

    for (idx in (0 until array.size - 1).reversed()) {
        if (array[idx] <= min) {
            min = array[idx]
        } else {
            start = idx
        }
    }

    return Pair(start, end)
}

