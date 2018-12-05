package Chapter4_6

fun main(args: Array<String>) {
    test()
    otherTest()
}

fun otherTest() {
    val four = BinaryTreeNode(value = 4)
    val seven = BinaryTreeNode(value = 7)
    val six = BinaryTreeNode(
            value = 6,
            left = four,
            right = seven
    )

    val ten = BinaryTreeNode(
            value = 10,
            right = BinaryTreeNode(
                    value = 14,
                    left = BinaryTreeNode(value = 13)
            )
    )


    val bstRoot = BinaryTreeNode(
            value = 8,
            left = BinaryTreeNode(
                    value = 3,
                    left = BinaryTreeNode(value = 1),
                    right = six
            ),
            right = ten
    )

    require(findNextNode(four) == six)
    require(findNextNode(six) == seven)
    require(findNextNode(seven) == bstRoot)
    require(findNextNode(bstRoot) == ten)
}

fun test() {
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

    val expected = listOf(
            one, five, eight, ten, twelve, fifteen, twenty, twentyTwo,
            twentyFive, twentyEight, thirty, thirtySix, thirtyEight, forty,
            fortyFive, fortyEight, fifty
    )

    var idx = 0
    var current: BinaryTreeNode? = one

    while (current != null) {
        require(expected[idx] == current)

        current = findNextNode(current)
        idx++
    }
}

fun findNextNode(current: BinaryTreeNode): BinaryTreeNode? {
    if (current.right != null) {
        return getLeftmost(current.right)
    }

    return getNextParent(current).parent
}

tailrec fun getLeftmost(node: BinaryTreeNode): BinaryTreeNode {
    if (node.left != null) {
        return getLeftmost(node.left)
    }

    return node
}

tailrec fun getNextParent(node: BinaryTreeNode): BinaryTreeNode {
    val parent = node.parent
    if (parent != null && node.isRightChild()) {
        return getNextParent(parent)
    }

    return node
}
