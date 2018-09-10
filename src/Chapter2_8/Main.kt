package Chapter2_8

import Chapter2_1.LinkedList
import Chapter2_1.Node

fun main(args: Array<String>) {
    testInnerLoop()
    testNonInnerLoop()
}

fun testNonInnerLoop() {
    val ints = intArrayOf(1, 2, 3, 4, 5, 6)
    val list = LinkedList.fromArray(ints)

    require(detectLoop(list) == null)
}

fun testInnerLoop() {
    val ints = intArrayOf(1, 2, 3, 4, 5, 6)
    val list = LinkedList.fromArray(ints)
    val innerNode = list.asSequence().drop(3).first()

    list.getTailNode()!!.next = innerNode

    require(detectLoop(list) == innerNode)
}

fun detectLoop(list: LinkedList): Node? {
    val zippedSequence =
            list.asSequence() zip list.asSequence().filterIndexed { index, _ -> index % 2 == 0 }

    for ((slowNode, fastNode) in zippedSequence.drop(1)) {
        if (slowNode == fastNode) {
            return slowNode
        }
    }

    return null
}
