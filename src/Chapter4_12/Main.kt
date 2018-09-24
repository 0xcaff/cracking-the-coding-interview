package Chapter4_12

import Chapter4_2.BinaryTreeNode

fun main(args: Array<String>) {
    testSimple()
    testShortening()
}

fun testSimple() {
    val rightRight = BinaryTreeNode(1)
    val right = BinaryTreeNode(1, right = rightRight)

    val left = BinaryTreeNode(2)

    val root = BinaryTreeNode(1, left, right)

    require(countPathsWithSum(3, root) == 2)
}

fun testShortening() {
    val left3 = BinaryTreeNode(1)
    val left2 = BinaryTreeNode(3, left = left3)
    val left1 = BinaryTreeNode(20, left = left2)

    val right2 = BinaryTreeNode(1)
    val right1 = BinaryTreeNode(1, right = right2)

    val root = BinaryTreeNode(1, left1, right1)

    require(countPathsWithSum(4, root) == 1)
}

fun countPathsWithSum(sum: Int, root: BinaryTreeNode, trail: List<Int> = listOf()): Int {
    var count = 0
    val (newTrail, trailSum) = shortenTrailUntilLessThan(sum, trail + root.value)

    if (trailSum == sum) {
        count++
    }

    val left = root.left
    if (left != null) {
        count += countPathsWithSum(sum, left, newTrail)
    }

    val right = root.right
    if (right != null) {
        count += countPathsWithSum(sum, right, newTrail)
    }

    return count
}

fun shortenTrailUntilLessThan(max: Int, trail: List<Int>): Pair<List<Int>, Int> {
    val output = trail.toMutableList()
    var sum = trail.sum()

    while (!output.isEmpty() && sum > max) {
        val front = output.removeAt(0)
        sum -= front
    }

    return Pair(output, sum)
}
