package Chapter8_4

import kotlin.math.pow

fun main(args: Array<String>) {
    require(powerset(intArrayOf(1, 2, 3)).size == 8)
    require(powerset(intArrayOf(1, 2, 3, 4)).size == 16)

    require(1.getBit(0))
    require(!1.getBit(1))
}

fun powerset(items: IntArray): List<IntArray> {
    val output = mutableListOf<IntArray>()

    for (i in 0 until 2.0.pow(items.size).toInt()) {
        var set = intArrayOf()

        for (bitIdx in 0 until items.size) {
            val isIn = i.getBit(bitIdx)

            if (isIn) {
                set += items[bitIdx]
            }
        }

        output.add(set)
    }

    return output
}

fun Int.getBit(idx: Int): Boolean = (this shr idx) and 0b1 == 1
