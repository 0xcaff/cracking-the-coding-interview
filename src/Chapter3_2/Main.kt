package Chapter3_2

fun main(args: Array<String>) {
    testPushMin()
}

fun testPushMin() {
    val stack = MinStack()
    stack.push(10)
    stack.push(11)
    stack.push(12)

    require(stack.min() == 10)

    stack.push(3)
    require(stack.min() == 3)

    stack.pop()
    require(stack.min() == 10)
}

class MinStack {
    data class Node(
            val value: Int,
            var next: Node?,
            val substackMin: Int
    )

    private var top: Node? = null

    fun push(value: Int) {
        val top = this.top
        this.top = Node(value, top, if (top == null || value <= top.substackMin) value else top.substackMin)
    }

    fun pop(): Int {
        val top = this.top!!
        this.top = top.next
        return top.value
    }

    fun min(): Int {
        return top!!.substackMin
    }
}
