package Chapter4_2

data class BSTNode(
        val value: Int,
        var left: BSTNode? = null,
        var right: BSTNode? = null
)

fun makeMinimalBinarySearchTree(values: IntArray): BSTNode? {
    if (values.size == 1) {
        return BSTNode(values[0])
    }

    if (values.isEmpty()) {
        return null
    }

    val middleIdx = values.size / 2
    val middle = values[middleIdx]

    val lhsArray = values.slice(0 until middleIdx).toIntArray()
    val lhs = makeMinimalBinarySearchTree(lhsArray)

    val rhsArray = values.slice(middleIdx + 1 until values.size).toIntArray()
    val rhs = makeMinimalBinarySearchTree(rhsArray)

    return BSTNode(
            value = middle,
            left = lhs,
            right = rhs
    )
}
