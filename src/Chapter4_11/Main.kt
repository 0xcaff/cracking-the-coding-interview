package Chapter4_11

fun main(args: Array<String>) {
    testRootOnly()
    test()
}

fun testRootOnly() {
    val node = BinaryTreeNode(null)
    require(node == node.getRandom())
}

fun test() {
    val root = BinaryTreeNode(null)

    val left = BinaryTreeNode(root)
    root.addLeftChild(left)

    val right = BinaryTreeNode(root)
    root.addRightChild(right)

    val rightRight = BinaryTreeNode(right)
    right.addRightChild(rightRight)

    require(left == root.getRandom { 0 })
    require(right == root.getRandom { 1 })

    require(root == root.getRandom { 4 })
    var switch = false
    require(rightRight == root.getRandom {
        if (!switch) {
            switch = true
            1
        } else {
            0
        }
    })
}
