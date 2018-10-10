package Chapter8_2

fun main(args: Array<String>) {
    require(findPath(Cell(3, 3), setOf()) != null)
    require(findPath(Cell(3, 3), setOf(Cell(3, 0))) != null)
}

data class Cell(val row: Int, val column: Int) {
    fun left(): Cell = Cell(row, column - 1)
    fun up(): Cell = Cell(row - 1, column)
    fun isStart(): Boolean = row == 0 && column == 0
    fun outOfBounds(): Boolean = row < 0 || column < 0
}

fun findPath(end: Cell, excluded: Set<Cell>): List<Cell>? {
    println(end)
    if (excluded.contains(end) || end.outOfBounds()) {
        return null
    }

    if (end.isStart()) {
        return listOf(end)
    }

    val left = end.left()
    val leftPath = findPath(left, excluded)
    if (leftPath != null) {
        return leftPath + end
    }

    val up = end.up()
    val upPath = findPath(up, excluded)
    if (upPath != null) {
        return upPath + end
    }

    return null
}
