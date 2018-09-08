package Chapter2_2

import Chapter2_1.LinkedList

fun main(args: Array<String>) {
    require(test(intArrayOf(0, 1, 2, 3), 0, 3))
    require(test(intArrayOf(0, 1, 2, 3), 1, 2))
    require(test(intArrayOf(0, 1, 2, 3), 2, 1))
    require(test(intArrayOf(0, 1, 2, 3), 3, 0))
}

fun test(input: IntArray, k: Int, expected: Int): Boolean
    = getKthToLast(LinkedList.fromArray(input), k) == expected

fun getKthToLast(list: LinkedList, k: Int): Int {
    var trailingNode = list.head
    var leadingNode = list.head

    for (i in 0 until k) {
        leadingNode = leadingNode.next!!
    }

    while (leadingNode.next != null) {
        leadingNode = leadingNode.next!!
        trailingNode = trailingNode.next!!
    }

    return trailingNode.data
}
