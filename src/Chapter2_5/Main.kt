package Chapter2_5

import Chapter2_1.LinkedList
import Chapter2_1.Node

fun main(args: Array<String>) {
    require(test(intArrayOf(7, 1, 6), intArrayOf(5, 9, 2), intArrayOf(2, 1, 9)))
    require(test(intArrayOf(2, 7, 1, 6), intArrayOf(5, 9, 2), intArrayOf(7, 6, 4, 6)))
    require(test(intArrayOf(3, 2, 7, 1, 6), intArrayOf(5, 9, 2), intArrayOf(8, 1, 0, 2, 6)))
    require(test(intArrayOf(9, 7, 8), intArrayOf(6, 8, 5), intArrayOf(5, 6, 4, 1)))
}

fun test(aDigits: IntArray, bDigits: IntArray, sumDigits: IntArray): Boolean {
    val a = LinkedList.fromArray(aDigits)
    val b = LinkedList.fromArray(bDigits)

    val actualSum = add(a, b).asArray()

    return actualSum.contentEquals(sumDigits)
}

fun add(a: LinkedList, b: LinkedList): LinkedList {
    val beforeHead = Node(-1)
    var tail = beforeHead

    var aNode: Node? = a.head
    var bNode: Node? = b.head
    var carry = 0

    while (aNode != null || bNode != null) {
        val placeSum = aNode.getData() + bNode.getData() + carry
        val place = placeSum % 10
        carry = (placeSum - place) / 10

        val placeNode = Node(place)
        tail.next = placeNode
        tail = placeNode

        aNode = aNode?.next
        bNode = bNode?.next
    }

    if (carry != 0) {
        val placeNode = Node(carry)
        tail.next = placeNode
    }

    return LinkedList(beforeHead.next!!)
}

fun Node?.getData(): Int {
    if (this == null) {
        return 0
    }

    return this.data
}
