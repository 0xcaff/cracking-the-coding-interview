package Chapter4_3

import Chapter4_2.BinaryTreeNode
import java.util.*

fun main(args: Array<String>) {
    test()
}

fun test() {
    val nine = BinaryTreeNode(9)
    val eighteen = BinaryTreeNode(18)
    val three = BinaryTreeNode(3, nine, eighteen)

    val seven = BinaryTreeNode(7)

    val twenty = BinaryTreeNode(20, three, seven)

    val twelve = BinaryTreeNode(12)
    val five = BinaryTreeNode(value = 5, right = twelve)

    val root = BinaryTreeNode(10, five, twenty)

    val actualDepths = listOfDepths(root)
    val expectedDepths = listOf(
            listOf(root),
            listOf(five, twenty),
            listOf(twelve, three, seven),
            listOf(nine, eighteen)
    )


    require(expectedDepths.equals(actualDepths))
}

fun listOfDepths(root: BinaryTreeNode): List<List<BinaryTreeNode>> {
    val output = LinkedList<List<BinaryTreeNode>>()
    val rootLinkedList = listOf(root)

    var previous = rootLinkedList

    while (previous.isNotEmpty()) {
        output.add(previous)
        previous = getNextLinkedListFromPrevious(previous)
    }

    return output
}

fun getNextLinkedListFromPrevious(previous: List<BinaryTreeNode>): List<BinaryTreeNode> {
    val output = LinkedList<BinaryTreeNode>()

    for (node in previous) {
        val left = node.left
        if (left != null) {
            output.add(left)
        }

        val right = node.right
        if (right != null) {
            output.add(right)
        }
    }

    return output
}
