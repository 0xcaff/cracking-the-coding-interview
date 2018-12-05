package InorderTraversals.Stack

import java.util.*

class BinaryTreeNode(
        var left: BinaryTreeNode? = null,
        var right: BinaryTreeNode? = null,
        val data: Int
)

fun main(args: Array<String>) {
    val bstRoot = BinaryTreeNode(
            data = 8,
            left = BinaryTreeNode(
                    data = 3,
                    left = BinaryTreeNode(data = 1),
                    right = BinaryTreeNode(
                            data = 6,
                            left = BinaryTreeNode(data = 4),
                            right = BinaryTreeNode(data = 7)
                    )
            ),
            right = BinaryTreeNode(
                    data = 10,
                    right = BinaryTreeNode(
                            data = 14,
                            left = BinaryTreeNode(data = 13)
                    )
            )
    )

    inOrderIterative(bstRoot)
}

fun inOrderIterative(root: BinaryTreeNode) {
    val stack = Stack<BinaryTreeNode>()
    var current: BinaryTreeNode? = root

    while (!stack.empty() || current != null) {
        if (current != null) {
            stack.push(current)
            current = current.left
        } else {
            current = stack.pop()
            println(current.data)

            current = current.right
        }
    }
}
