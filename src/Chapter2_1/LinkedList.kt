package Chapter2_1

class Node(var data: Int) {
    var next: Node? = null

    fun dropNext() {
        this.next = this.next?.next
    }

    companion object {
        fun fromArray(arr: IntArray): Node {
            var head: Node? = null
            var current: Node? = null

            for (item in arr) {
                val nextNode = Node(item)

                if (head == null) {
                    head = nextNode
                } else {
                    current!!.next = nextNode
                }

                current = nextNode
            }

            return head!!
        }
    }
}

class LinkedList(val head: Node) : Iterable<Node> {
    override fun iterator(): Iterator<Node> = LinkedListIterator(this)

    fun getTailNode(): Node? =
            this.last()

    fun asArray(): IntArray =
            this.asSequence()
                    .map { e -> e.data }
                    .toList()
                    .toTypedArray()
                    .toIntArray()

    companion object {
        fun fromArray(arr: IntArray): LinkedList {
            val head = Node.fromArray(arr)
            return LinkedList(head)
        }
    }
}

class LinkedListIterator(list: LinkedList) : Iterator<Node> {
    private var current: Node? = list.head

    override fun hasNext(): Boolean = current != null

    override fun next(): Node {
        val oldCurrent = current!!
        current = oldCurrent.next

        return oldCurrent
    }
}
