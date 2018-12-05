package InorderTraversals.Parent

import Chapter4_6.BinaryTreeNode

fun main(args: Array<String>) {
    val bstRoot = BinaryTreeNode(
            value = 8,
            left = BinaryTreeNode(
                    value = 3,
                    left = BinaryTreeNode(value = 1),
                    right = BinaryTreeNode(
                            value = 6,
                            left = BinaryTreeNode(value = 4),
                            right = BinaryTreeNode(value = 7)
                    )
            ),
            right = BinaryTreeNode(
                    value = 10,
                    right = BinaryTreeNode(
                            value = 14,
                            left = BinaryTreeNode(value = 13)
                    )
            )
    )

    inOrderIterative(bstRoot)
}

fun inOrderIterative(root: BinaryTreeNode) {
    var node: BinaryTreeNode? = getLeftmost(root)

    while (node != null) {
        println(node.value)

        val right = node.right
        if (right != null) {
            node = getLeftmost(right)
        } else {
            while (node!!.parent != null && node.isRightChild()) {
                node = node.parent
            }

            node = node.parent
        }
    }
}

fun getLeftmost(startingNode: BinaryTreeNode): BinaryTreeNode {
    var node = startingNode
    while (node.left != null) {
        node = node.left!!
    }

    return node
}

