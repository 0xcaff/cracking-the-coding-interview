package Chapter4_9

import Chapter4_2.BinaryTreeNode

fun main(args: Array<String>) {
    testSimple()
    testLarge()
}

fun testSimple() {
    val a = BinaryTreeNode(1)
    val b = BinaryTreeNode(2)

    val root = BinaryTreeNode(3, a, b)

    val expected =
            listOf(
                    listOf(root, a, b),
                    listOf(root, b, a)
            )

    val actual = makeBinarySearchTreeSequences(root)

    require(actual == expected)
}

fun testLarge() {
    val one = BinaryTreeNode(1)
    val eight = BinaryTreeNode(8)

    val five = BinaryTreeNode(5, one, eight)

    val fifteen = BinaryTreeNode(15)
    val twelve = BinaryTreeNode(12, right = fifteen)

    val ten = BinaryTreeNode(10, five, twelve)

    val twentyTwo = BinaryTreeNode(22)

    val twenty = BinaryTreeNode(20, ten, twentyTwo)

    val twentyEight = BinaryTreeNode(28)
    val thirty = BinaryTreeNode(30, left = twentyEight)

    val thirtyEight = BinaryTreeNode(38)

    val fortyFive = BinaryTreeNode(45)
    val fifty = BinaryTreeNode(50)

    val fortyEight = BinaryTreeNode(48, fortyFive, fifty)

    val forty = BinaryTreeNode(40, thirtyEight, fortyEight)

    val thirtySix = BinaryTreeNode(36, thirty, forty)
    val twentyFive = BinaryTreeNode(25, twenty, thirtySix)

    val result = makeBinarySearchTreeSequences(twentyFive)
    println(result)
}

fun makeBinarySearchTreeSequences(
        root: BinaryTreeNode?
): List<List<BinaryTreeNode>> {
    if (root == null) {
        return listOf()
    }

    val leftTreeSequences = makeBinarySearchTreeSequences(root.left)
    val rightTreeSequences = makeBinarySearchTreeSequences(root.right)

    if (leftTreeSequences.isEmpty() && rightTreeSequences.isEmpty()) {
        return listOf(listOf(root))
    }

    val output = mutableListOf<List<BinaryTreeNode>>()
    if (rightTreeSequences.isEmpty()) {
        output += leftTreeSequences.map { listOf(root) + it }
    }

    if (leftTreeSequences.isEmpty()) {
        output += rightTreeSequences.map { listOf(root) + it }
    }

    if (leftTreeSequences.isNotEmpty() && rightTreeSequences.isNotEmpty()) {
        for (leftTreeSequence in leftTreeSequences) {
            for (rightTreeSequence in rightTreeSequences) {
                output += listOf(root) + leftTreeSequence + rightTreeSequence
                output += listOf(root) + rightTreeSequence + leftTreeSequence
            }
        }
    }

    return output
}
