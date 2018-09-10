package Chapter2_7

import Chapter2_1.LinkedList
import Chapter2_1.Node

fun main(args: Array<String>) {
    test(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))
    test(intArrayOf(1, 2, 3, 9), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))

    require(
            findFirstIntersection(
                    LinkedList.fromArray(intArrayOf(1, 2, 3, 4)),
                    LinkedList.fromArray(intArrayOf(1, 2, 3, 4, 5))
            ) == null
    )
}

fun test(prefixA: IntArray, prefixB: IntArray, suffix: IntArray) {
    val prefixAList = LinkedList.fromArray(prefixA)
    val prefixBList = LinkedList.fromArray(prefixB)
    val suffixList = LinkedList.fromArray(suffix)

    prefixAList.getTailNode()!!.next = suffixList.head
    prefixBList.getTailNode()!!.next = suffixList.head

    val actualIntersection = findFirstIntersection(prefixAList, prefixBList)

    require(suffixList.head == actualIntersection)
}

fun findFirstIntersection(a: LinkedList, b: LinkedList): Node? {
    val aTraversal = a.getTail()
    val bTraversal = b.getTail()

    if (aTraversal.tail != bTraversal.tail) {
        return null
    }

    val (shorter, longer) = TraversalInformation.getShorterLonger(aTraversal, bTraversal)

    val longerDifference = longer.size - shorter.size

    val shorterSequence = shorter.linkedList.asSequence()
    val longerSequence = longer.linkedList.asSequence()

    for ((shorterNode, longerNode) in shorterSequence zip longerSequence.drop(longerDifference)) {
        if (shorterNode == longerNode) {
            return shorterNode
        }
    }

    throw IllegalStateException("Found a matching tail, but couldn't find the shared node.")
}

data class TraversalInformation(val size: Int, val tail: Node, val linkedList: LinkedList) {
    companion object {
        fun getShorterLonger(a: TraversalInformation, b: TraversalInformation): Pair<TraversalInformation, TraversalInformation> {
            return if (a.size <= b.size) {
                Pair(a, b)
            } else {
                Pair(b, a)
            }
        }
    }
}

fun LinkedList.getTail(): TraversalInformation {
    val (size, tail) = this.asSequence()
            .withIndex()
            .map { indexedValue -> Pair(indexedValue.index + 1, indexedValue.value) }
            .last()

    return TraversalInformation(size, tail, this)
}
