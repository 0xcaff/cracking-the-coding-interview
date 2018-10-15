package Chapter8_12

import kotlin.math.absoluteValue

fun placeQueens(row: Int, columns: IntArray): List<IntArray> {
    if (row == 8) {
        return listOf(columns.clone())
    }

    val output = mutableListOf<IntArray>()
    for (columnIdx in 0 until 8) {
        if (checkValid(columns, row, columnIdx)) {
            columns[row] = columnIdx
            output.addAll(placeQueens(row + 1, columns))
        }
    }

    return output
}

fun checkValid(columns: IntArray, rowIdx: Int, colIdx: Int): Boolean {
    for (checkRowIdx in 0 until rowIdx) {
        val checkColIdx = columns[checkRowIdx]

        if (checkColIdx == colIdx) {
            return false
        }

        val rowDiff = rowIdx - checkRowIdx
        val colDiff = (colIdx - checkColIdx).absoluteValue

        if (rowDiff == colDiff) {
            return false
        }
    }

    return true
}

fun toString(columns: IntArray): String {
    var output = ""

    for (rowIdx in 0 until columns.size) {
        var row = ""

        for (colIdx in 0 until columns.size) {
            val atPosition = if (columns[colIdx] == rowIdx) {
                "Q"
            } else {
                " "
            }

            row += atPosition
        }

        output += "$row\n"
    }

    return output
}

fun main(args: Array<String>) {
    require(placeQueens(0, IntArray(8)).size == 92)
    println(placeQueens(0, IntArray(8)).map { toString(it) }.joinToString("\n\n"))
}

