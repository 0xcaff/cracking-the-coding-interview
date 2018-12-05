package Chapter17_12

import java.util.*

class BiNode(var left: BiNode? = null, var right: BiNode? = null, val data: Int)

fun BiNode?.linkNext(next: BiNode?) {
    if (this != null) {
        this.right = next
    }

    if (next != null) {
        next.left = this
    }
}

fun BiNode?.traverse() {
    var head = this

    while (head != null) {
        println(head.data)
        head = head.right
    }
}

fun main(args: Array<String>) {
    val bstRoot = BiNode(
            data = 8,
            left = BiNode(
                    data = 3,
                    left = BiNode(data = 1),
                    right = BiNode(
                            data = 6,
                            left = BiNode(data = 4),
                            right = BiNode(data = 7)
                    )
            ),
            right = BiNode(
                    data = 10,
                    left = BiNode(
                            data = 14,
                            left = BiNode(data = 13)
                    )
            )
    )

    val head = treeToList(bstRoot)
    head.traverse()
}

fun treeToList(root: BiNode): BiNode? {
    val superHead = BiNode(data = -1)
    var lastNode = superHead

    val stack = Stack<BiNode>()
    var node: BiNode? = root

    while (node != null || !stack.isEmpty()) {
        if (node != null) {
            stack.push(node)
            node = node.left
        } else {
            val current = stack.pop()
            lastNode.linkNext(current)
            lastNode = current
            node = current.right
        }
    }

    superHead.right?.left = null
    return superHead.right
}
