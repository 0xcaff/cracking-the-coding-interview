package Chapter16_19

fun main(args: Array<String>) {
    val pond = arrayOf(
            intArrayOf(0, 2, 1, 0),
            intArrayOf(0, 1, 0, 1),
            intArrayOf(1, 1, 0, 1),
            intArrayOf(0, 1, 0, 1)
    )

    val visited = Array(pond.size) { it -> BooleanArray(pond[it].size) }
    require(getPondSize(visited, pond, 0, 0) == 2)

    require(pondSizes(pond) == listOf(2, 4, 1))
}

/**
 * You have an integer matrix representing a plot of land where the value at
 * that location represents the height above sea level. A value of zero
 * indicates water. A pond is a region of water connected vertically,
 * horizontally, or diagonally. The size of the pond is the total number of
 * connected water cells. Write a method to compute the sizes of all ponds in
 * the matrix.
 */
fun pondSizes(plot: Array<IntArray>): List<Int> {
    val visited = Array(plot.size) { it -> BooleanArray(plot[it].size) }
    val ponds = mutableListOf<Int>()

    for (rowIdx in 0 until plot.size) {
        val row = plot[rowIdx]

        for (colIdx in 0 until row.size) {
            val sizeAt = getPondSize(visited, plot, rowIdx, colIdx)
            if (sizeAt != 0) {
                ponds += sizeAt
            }
        }
    }

    return ponds
}

val neighborDisplacements = arrayOf(
        Displacement(1, 1),
        Displacement(0, 1),
        Displacement(-1, 1),

        Displacement(-1, 0),
        Displacement(1, 0),

        Displacement(1, -1),
        Displacement(0, -1),
        Displacement(-1, -1)
)

data class Displacement(val rowDiff: Int, val colDiff: Int)

data class Position(var rowIdx: Int, val colIdx: Int) {
    fun withinBounds(plot: Array<IntArray>): Boolean
        = this.rowIdx >= 0 && this.rowIdx < plot.size &&
            this.colIdx >= 0 && this.colIdx < plot[0].size
}

fun getPondSize(visited: Array<BooleanArray>, plot: Array<IntArray>, rowIdx: Int, colIdx: Int): Int {
    if (visited[rowIdx][colIdx] || plot[rowIdx][colIdx] != 0) {
        return 0
    }

    visited[rowIdx][colIdx] = true

    return 1 + neighborDisplacements
            .map { Position(rowIdx + it.rowDiff, colIdx + it.colDiff) }
            .filter { it.withinBounds(plot) }
            .map { getPondSize(visited, plot, it.rowIdx, it.colIdx) }
            .sum()
}
