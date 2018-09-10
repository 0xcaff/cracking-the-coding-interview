package Chapter2_4

import Chapter2_1.LinkedList
import Chapter2_1.Node

fun main(args: Array<String>) {
    require(test(5, intArrayOf(3, 5, 8, 5, 10, 2, 1)))
    require(!check(5, intArrayOf(3, 5, 8, 5, 10, 2, 1)))
}

fun test(pivot: Int, list: IntArray): Boolean {
    val linkedList = LinkedList.fromArray(list)
    partition(pivot, linkedList)
    return check(pivot, linkedList.asArray())
}

fun check(pivot: Int, list: IntArray): Boolean {
    var passedPivot = false

    for (item in list) {
        val itemOnRightOfPivot = item >= pivot
        if (itemOnRightOfPivot) {
            passedPivot = true
        }

        if (!passedPivot && itemOnRightOfPivot) {
            return false
        }

        if (passedPivot && !itemOnRightOfPivot) {
            return false
        }
    }

    return true
}

fun partition(pivot: Int, list: LinkedList) {
    val originalTail = list.getTailNode()!!
    var tail = originalTail

    var lastNode: Node? = null
    for (node in list) {
        if (node == originalTail) {
            break
        }

        if (node.data >= pivot) {
            if (lastNode != null) {
                lastNode.next = node.next
            }

            tail.next = node
            node.next = null
            tail = node
        } else {
            lastNode = node
        }
    }
}
