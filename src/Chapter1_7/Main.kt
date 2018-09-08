package Chapter1_7

fun main(args: Array<String>) {
    require(test(
            arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(4, 5, 6),
                    intArrayOf(7, 8, 9)
            ),
            arrayOf(
                    intArrayOf(7, 4, 1),
                    intArrayOf(8, 5, 2),
                    intArrayOf(9, 6, 3)
            )
    ))

    require(test(
            arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(5, 6, 7, 8),
                    intArrayOf(9, 10, 11, 12),
                    intArrayOf(13, 14, 15, 16)
            ),
            arrayOf(
                    intArrayOf(13, 9, 5, 1),
                    intArrayOf(14, 10, 6, 2),
                    intArrayOf(15, 11, 7, 3),
                    intArrayOf(16, 12, 8, 4)
            )
    ))

    require(test(
            arrayOf(
                    intArrayOf(1, 2, 3, 4, 5),
                    intArrayOf(6, 7, 8, 9, 10),
                    intArrayOf(11, 12, 13, 14, 15),
                    intArrayOf(16, 17, 18, 19, 20),
                    intArrayOf(21, 22, 23, 24, 25)
            ),
            arrayOf(
                    intArrayOf(21, 16, 11, 6, 1),
                    intArrayOf(22, 17, 12, 7, 2),
                    intArrayOf(23, 18, 13, 8, 3),
                    intArrayOf(24, 19, 14, 9, 4),
                    intArrayOf(25, 20, 15, 10, 5)
            )
    ))
}

fun test(input: Array<IntArray>, expected: Array<IntArray>): Boolean {
    rotateInPlace(input)
    return input.contentDeepEquals(expected)
}

/**
 * Rotates the input array 90deg clockwise in place.
 */
fun rotateInPlace(input: Array<IntArray>) {
    val n = input.size
    val maxDepth = n / 2

    for (currentDepth in 0..maxDepth) {
        for (idx in currentDepth until n - currentDepth - 1) {
            rotatePosition(input, currentDepth, idx, n)
        }
    }
}

fun rotatePosition(input: Array<IntArray>, rowIdx: Int, colIdx: Int, n: Int) {
    val maxIdx = n - 1

    val top = move(input, rowIdx, colIdx, 0)
    val right = move(input, colIdx, maxIdx - rowIdx, top)
    val bottom = move(input, maxIdx - rowIdx, maxIdx - colIdx, right)
    val left = move(input, maxIdx - colIdx, rowIdx, bottom)

    move(input, rowIdx, colIdx, left)
}

fun move(input: Array<IntArray>, rowIdx: Int, colIdx: Int, value: Int): Int {
    val temp = input[rowIdx][colIdx]
    input[rowIdx][colIdx] = value

    return temp
}

