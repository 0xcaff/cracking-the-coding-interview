package Spiral

fun main(args: Array<String>) {
    require(spiralOrder(
            arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(4, 5, 6),
                    intArrayOf(7, 8, 9)
            )
    ).contentEquals(intArrayOf(1, 2, 3, 6, 9, 8, 7, 4, 5)))

    require(spiralOrder(
            arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(3, 4)
            )
    ).contentEquals(intArrayOf(1, 2, 4, 3)))

    require(spiralOrder(
            arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(5, 6, 7, 8),
                    intArrayOf(9, 10, 11, 12),
                    intArrayOf(13, 14, 15, 16)
            )
    ).contentEquals(intArrayOf(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10)))

    require(spiralOrder(
            arrayOf(
                    intArrayOf(1, 2, 3, 4, 5),
                    intArrayOf(6, 7, 8, 9, 10),
                    intArrayOf(11, 12, 13, 14, 15),
                    intArrayOf(16, 17, 18, 19, 20),
                    intArrayOf(21, 22, 23, 24, 25)

            )
    ).contentEquals(intArrayOf(
            1, 2, 3, 4, 5, 10, 15, 20, 25, 24, 23, 22, 21, 16, 11, 6, 7, 8, 9, 14, 19, 18, 17, 12, 13)
    ))
}

fun spiralOrder(rows: Array<IntArray>): IntArray {
    val output = mutableListOf<Int>()
    val n = rows.size
    val maxDepth = n / 2

    for (depth in 0..maxDepth) {
        output += spiralAtDepth(depth, rows)
    }

    return output.toIntArray()
}

fun spiralAtDepth(depth: Int, rows: Array<IntArray>): List<Int> {
    val startIndex = depth
    val endIndex = rows.lastIndex - depth

    if (startIndex == endIndex) {
        // Center
        return listOf(rows[startIndex][endIndex])
    }

    val output = mutableListOf<Int>()

    // Top
    for (colIdx in startIndex..endIndex) {
        output += rows[startIndex][colIdx]
    }

    // Right
    for (rowIdx in (startIndex + 1)..(endIndex - 1)) {
        output += rows[rowIdx][endIndex]
    }

    // Bottom
    for (colIdx in (startIndex..endIndex).reversed()) {
        output += rows[endIndex][colIdx]
    }

    // Left
    for (rowIdx in ((startIndex + 1)..(endIndex - 1)).reversed()) {
        output += rows[rowIdx][startIndex]
    }

    return output
}

