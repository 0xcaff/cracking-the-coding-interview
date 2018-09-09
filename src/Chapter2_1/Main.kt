package Chapter2_1

fun main(args: Array<String>) {
    require(test(intArrayOf(1, 1, 2, 4, 5, 8, 5), intArrayOf(1, 2, 4, 5, 8)))
    require(test(intArrayOf(1, 2, 3, 4, 5), intArrayOf(1, 2, 3, 4, 5)))
}

fun test(input: IntArray, expected: IntArray): Boolean {
    val linkedList = LinkedList.fromArray(input)
    removeDuplicates(linkedList)

    val output = linkedList.asArray()
    return expected.contentEquals(output)
}

fun removeDuplicates(list: LinkedList) {
    val seen = HashSet<Int>()
    val previousNode = Node(-1)
    previousNode.next = list.head

    var current = previousNode

    while (current.next != null) {
        val next: Node = current.next!!

        if (seen.contains(next.data)) {
            current.dropNext()
        } else {
            seen.add(next.data)
        }

        current = next
    }
}
