package Chapter4_2

import Chapter4_4.Balanced
import Chapter4_4.getBalancingForSubtree
import Chapter4_5.isBinarySearchTree

fun main(args: Array<String>) = test()

fun test() {
    val root = makeMinimalBinarySearchTree(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))

    require(getBalancingForSubtree(root) is Balanced)
    require(isBinarySearchTree(root!!))
}

fun makeMinimalBinarySearchTree(values: IntArray): BinaryTreeNode? {
    if (values.size == 1) {
        return BinaryTreeNode(values[0])
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

    return BinaryTreeNode(
            value = middle,
            left = lhs,
            right = rhs
    )
}
