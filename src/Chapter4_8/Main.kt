package Chapter4_8

import Chapter4_2.BinaryTreeNode

fun main(args: Array<String>) {
    testNotInTree()
    testInTree()
}

fun testNotInTree() {
    val lhs = BinaryTreeNode(10)
    val rhs = BinaryTreeNode(20)

    val root = BinaryTreeNode(30, lhs, rhs)
    val extra = BinaryTreeNode(30)

    require(findFirstCommonAncestor(root, lhs, extra) == null)
}

fun testInTree() {
    val two = BinaryTreeNode(2)
    val six = BinaryTreeNode(6)

    val four = BinaryTreeNode(4, two, six)

    val twenty = BinaryTreeNode(20)
    val ten = BinaryTreeNode(10, right = twenty)

    val eight = BinaryTreeNode(8, four, ten)

    require(findFirstCommonAncestor(eight, two, six) == four)
    require(findFirstCommonAncestor(eight, ten, six) == eight)
    require(findFirstCommonAncestor(eight, ten, twenty) == ten)
}

fun findFirstCommonAncestor(
        root: BinaryTreeNode,
        a: BinaryTreeNode,
        b: BinaryTreeNode
): BinaryTreeNode? {
    val isAOrB = { node: BinaryTreeNode -> node == a || node == b}

    val rootHasAOrB = isAOrB(root)

    val left = root.left
    val leftHasAOrB = if (left != null) findPreOrder(left, isAOrB) else false

    val right = root.right
    val rightHasAOrB = if (right != null) findPreOrder(right, isAOrB) else false

    if (twoOfThree(leftHasAOrB, rightHasAOrB, rootHasAOrB)) {
        return root
    }

    if (leftHasAOrB) {
        return findFirstCommonAncestor(left!!, a, b)
    }

    if (rightHasAOrB) {
        return findFirstCommonAncestor(right!!, a, b)
    }

    return null
}

fun twoOfThree(a: Boolean, b: Boolean, c: Boolean): Boolean
    = booleanArrayOf(a, b, c).filter { it }.size == 2

fun findPreOrder(
        root: BinaryTreeNode,
        condition: (node: BinaryTreeNode) -> Boolean
): Boolean {
    if (condition(root)) {
        return true
    }

    val left = root.left
    if (left != null) {
        val leftTrail = findPreOrder(left, condition)
        if (leftTrail) {
            return true
        }
    }

    val right = root.right
    if (right != null) {
        val rightTrail = findPreOrder(right, condition)
        if (rightTrail) {
            return true
        }
    }

    return false
}
