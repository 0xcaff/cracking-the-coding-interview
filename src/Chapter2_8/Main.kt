package Chapter2_8

import Chapter2_1.LinkedList
import Chapter2_1.Node

fun main(args: Array<String>) {
    testInnerLoop()
    testNonInnerLoop()
    testLongInnerLoop()
}

fun testLongInnerLoop() {
    val ints = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    val list = LinkedList.fromArray(ints)
    val innerNode = list.asSequence().drop(3).first()

    list.getTailNode()!!.next = innerNode

    require(detectLoop(list) == innerNode)
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
    var slow: Node? = list.head
    var fast: Node? = list.head

    while (fast?.next != null) {
        slow = slow!!.next
        fast = fast.next!!.next

        if (slow == fast) {
            slow = list.head

            while (slow != fast) {
                slow = slow!!.next
                fast = fast!!.next
            }

            return fast
        }
    }

    return null
}
