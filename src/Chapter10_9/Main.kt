package Chapter10_9

fun main(args: Array<String>) {
    test()
}

fun test() {
    val matrix = arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(3, 4, 5, 6),
            intArrayOf(5, 6, 6, 6),
            intArrayOf(6, 8, 9, 10)
    )

    require(sortedMatrixSearch(matrix, 8) == Pair(3, 1))
    require(sortedMatrixSearch(matrix, 20) == null)
}

fun sortedMatrixSearch(matrix: Array<IntArray>, value: Int): Pair<Int, Int>? {
    var row = 0
    var column = matrix[0].size - 1

    while (
        column >= 0 && column < matrix[0].size &&
        row >= 0 && row < matrix.size
    ) {
        val element = matrix[row][column]

        when {
            element == value -> return Pair(row, column)
            element > value -> column--
            element < value -> row++
        }
    }

    return null
}
