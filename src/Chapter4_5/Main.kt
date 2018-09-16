package Chapter4_5

import Chapter4_2.BinaryTreeNode

fun main(args: Array<String>) = test()

fun test() {
    testIsBinarySearchTree()
    testIsNotBinarySearchTree()
}

fun testIsNotBinarySearchTree() {
    val two = BinaryTreeNode(2)
    val twelve = BinaryTreeNode(12)

    val four = BinaryTreeNode(4, two, twelve)

    val twenty = BinaryTreeNode(20)
    val ten = BinaryTreeNode(10, right = twenty)

    val eight = BinaryTreeNode(8, four, ten)

    require(!isBinarySearchTree(eight))
}

fun testIsBinarySearchTree() {
    val two = BinaryTreeNode(2)
    val six = BinaryTreeNode(6)

    val four = BinaryTreeNode(4, two, six)

    val twenty = BinaryTreeNode(20)
    val ten = BinaryTreeNode(10, right = twenty)

    val eight = BinaryTreeNode(8, four, ten)

    require(isBinarySearchTree(eight))
}

data class Constraints(
        val lowerInclusiveBound: Int? = null,
        val upperExclusiveBound: Int? = null
) {
    fun isWithin(value: Int): Boolean {
        if (lowerInclusiveBound != null && value < lowerInclusiveBound) {
            return false
        }

        if (upperExclusiveBound != null && value >= upperExclusiveBound) {
            return false
        }

        return true
    }
}

fun isBinarySearchTree(
        root: BinaryTreeNode,
        constraints: Constraints = Constraints()
): Boolean {
    if (!constraints.isWithin(root.value)) {
        return false
    }

    val left = root.left
    if (left != null) {
        val leftConstraints = Constraints(
                upperExclusiveBound = root.value,
                lowerInclusiveBound = constraints.lowerInclusiveBound
        )

        if (!isBinarySearchTree(left, leftConstraints)) {
            return false
        }
    }

    val right = root.right
    if (right != null) {
        val rightConstraints = Constraints(
                upperExclusiveBound = constraints.upperExclusiveBound,
                lowerInclusiveBound = root.value
        )

        if (!isBinarySearchTree(right, rightConstraints)) {
            return false
        }
    }

    return true
}
