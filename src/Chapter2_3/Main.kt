package Chapter2_3

import Chapter2_1.LinkedList
import Chapter2_1.Node

fun main(args: Array<String>) {
    val list = LinkedList.fromArray(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8))
    val node = list.asSequence().find { e -> e.data == 3 }!!
    deleteCurrentNodeValue(node)

    val output = list.asSequence()
            .map { e -> e.data }
            .toList()
            .toTypedArray()
            .toIntArray();

    require(intArrayOf(1, 2, 4, 5, 6, 7, 8).contentEquals(output))
}

fun deleteCurrentNodeValue(node: Node) {
    var currentNode = node

    while (currentNode.next != null) {
        val next = currentNode.next!!
        currentNode.data = next.data

        if (next.next == null) {
            currentNode.next = null
        }

        currentNode = next
    }
}
