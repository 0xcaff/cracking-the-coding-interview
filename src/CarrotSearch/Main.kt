/*
A very hungry rabbit is placed in the center of of a garden, represented by a
rectangular N x M 2D matrix.

The values of the matrix will represent numbers of carrots available to the
rabbit in each square of the garden. If the garden does not have an exact
center, the rabbit should start in the square closest to the center with the
highest carrot count.

On a given turn, the rabbit will eat the carrots available on the square that it
is on, and then move up, down, left, or right, choosing the the square that has
the most carrots. If there are no carrots left on any of the adjacent squares,
the rabbit will go to sleep. You may assume that the rabbit will never have to
choose between two squares with the same number of carrots.

Write a function which takes a garden matrix and returns the number of carrots
the rabbit eats. You may assume the matrix is rectangular with at least 1 row
and 1 column, and that it is populated with non-negative integers.
*/

/*
This code is written in Kotlin. The best way to run it is probably the Kotlin
plugin in an IntelliJ IDE.
*/

package CarrotSearch

fun main(args: Array<String>) {
    val rows = arrayOf(
            intArrayOf(5, 7, 8, 6, 3),
            intArrayOf(0, 0, 7, 0, 4),
            intArrayOf(4, 6, 3, 4, 9),
            intArrayOf(3, 1, 0, 5, 8)
    )

    require(findStartPosition(rows) == Position(1, 2))
    require(countCarrots(rows) == 27)
}

/**
 * Counts the number of carrots the bunny can collect starting from the
 * starting point if he moves up, down, left or right and only visits the
 * adjacent cell with the most carrots stopping when he can't get to a cell
 * which is unvisited and has carrots.
 *
 * @param rows A matrix containing information about how many carrots are in
 * each cell. This value is mutated to reflect the state after the bunny has
 * consumed carrots along the path taken.
 *
 * @return The number of carrots the bunny collected.
 */
fun countCarrots(rows: Array<IntArray>): Int {
    val startingPosition = findStartPosition(rows)
    var currentPosition: Position = startingPosition
    var totalCarrotsConsumed = 0

    do {
        val carrotsAtPosition = currentPosition.getFrom(rows)
        totalCarrotsConsumed += carrotsAtPosition
        currentPosition.setTo(rows, 0)

        val nextPosition = currentPosition.getValidNeighbors(rows)
            .maxBy { neighbor -> neighbor.getFrom(rows) }

        if (nextPosition == null || nextPosition.getFrom(rows) == 0) {
            break
        }

        currentPosition = nextPosition
    } while (true)

    return totalCarrotsConsumed
}

/**
 * Container to hold a row index and column index. Provides helper methods for
 * use with matrices. Matrices are represented as 2d arrays (row major) with
 * columns of the same length.
 *
 * @property row the row index
 * @property col the column index
 */
data class Position(val row: Int, val col: Int) {
    fun getFrom(rows: Array<IntArray>): Int = rows[row][col]

    fun setTo(rows: Array<IntArray>, value: Int) {
        rows[row][col] = value
    }

    /**
     * Gets four (top, left, bottom, right) possible neighbors to this position.
     *
     * @return An array of neighboring positions.
     */
    private fun getPossibleNeighbors(): Array<Position> = arrayOf(
            Position(row = row - 1, col = col    ), // Up
            Position(row = row + 1, col = col    ), // Down
            Position(row = row,     col = col - 1), // Left
            Position(row = row,     col = col + 1)  // Right
    )

    /**
     * @return Whether or not this position is valid in the matrix [rows].
     */
    private fun isValidIn(rows: Array<IntArray>): Boolean
        = row >= 0 && row < rows.size && col >= 0 && col < rows[0].size

    /**
     * @return An array of neighbor positions which are valid in the provided
     * matrix [rows].
     */
    fun getValidNeighbors(rows: Array<IntArray>): List<Position>
        = getPossibleNeighbors().filter { it.isValidIn(rows) }
}

/**
 * Finds the start position for the bunny in the [rows] matrix. The start
 * position is the middle position in the matrix with the most carrots.
 *
 * @return The start position.
 */
fun findStartPosition(rows: Array<IntArray>): Position {
    val rowsCount = rows.size
    val colsCount = rows[0].size

    val centerRows = getCenterPositions(rowsCount)
    val centerCols = getCenterPositions(colsCount)

    var mostCarrotsPosition: Position? = null
    for (centerRow in centerRows) {
        for (centerCol in centerCols) {
            val centerRowIdx = centerRow - 1
            val centerColIdx = centerCol - 1

            val maybeMostCarrotsPosition = Position(centerRowIdx, centerColIdx)

            if (
                mostCarrotsPosition == null ||
                maybeMostCarrotsPosition.getFrom(rows) > mostCarrotsPosition.getFrom(rows)
            ) {
                mostCarrotsPosition = maybeMostCarrotsPosition
            }
        }
    }

    return mostCarrotsPosition!!
}

/**
 * If [count] is even, returns the middle two values, otherwise returns the
 * middle value. Values range from [1..[count]]
 */
fun getCenterPositions(count: Int): IntArray =
    if (count % 2 == 1) {
        intArrayOf(count / 2 + 1)
    } else {
        intArrayOf(count / 2, count / 2 + 1)
    }
