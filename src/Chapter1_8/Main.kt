package Chapter1_8

fun main(args: Array<String>) {
    test(
            arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(4, 0, 6),
                    intArrayOf(7, 8, 9)
            ),
            arrayOf(
                    intArrayOf(1, 0, 3),
                    intArrayOf(0, 0, 0),
                    intArrayOf(7, 0, 9)
            )
    )

    test(
            arrayOf(
                    intArrayOf(1, 2, 0),
                    intArrayOf(4, 3, 6),
                    intArrayOf(7, 8, 9)
            ),
            arrayOf(
                    intArrayOf(0, 0, 0),
                    intArrayOf(4, 3, 0),
                    intArrayOf(7, 8, 0)
            )
    )
}

fun test(input: Array<IntArray>, expected: Array<IntArray>) {
    zeroMatrix(input)
    require(input.contentDeepEquals(expected))
}

fun zeroMatrix(input: Array<IntArray>) {
    var firstRowHasZero = false
    var firstColumnHasZero = false

    for (rowIdx in 0 until input.size) {
        val row = input[rowIdx]
        val isFirstRow = rowIdx == 0

        for (colIdx in 0 until row.size) {
            val isZero = row[colIdx] == 0
            val isFirstCol = colIdx == 0

            if (!isZero) {
                continue
            }

            when {
                isFirstCol -> firstColumnHasZero = true
                isFirstRow -> firstRowHasZero = true
                else -> {
                    input[rowIdx][0] = 0
                    input[0][colIdx] = 0
                }
            }
        }
    }

    for (rowIdx in 0 until input.size) {
        val row = input[rowIdx]
        val rowIgnored = row[0] == 0;

        for (colIdx in 0 until row.size) {
            val colIgnored = input[0][colIdx] == 0;

            if (colIgnored || rowIgnored) {
                row[colIdx] = 0;
            }
        }
    }

    if (firstColumnHasZero) {
        for (rowIdx in 0 until input.size) {
            input[rowIdx][0] = 0
        }
    }

    if (firstRowHasZero) {
        for (colIdx in 0 until input[0].size) {
            input[0][colIdx] = 0
        }
    }
}
