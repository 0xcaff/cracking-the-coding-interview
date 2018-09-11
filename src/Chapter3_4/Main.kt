package Chapter3_4

import java.util.*

interface Queue<T> {
    fun add(item: T)

    fun remove(): T

    fun peek(): T

    fun isEmpty(): Boolean
}

class StackQueue<T> : Queue<T> {
    /**
     * Sorted from newest elements at the top to oldest elements at the bottom.
     */
    private val pushStack = Stack<T>()

    /**
     * Sorted from oldest elements at the top to newest elements at the bottom.
     */
    private val popStack = Stack<T>()

    override fun add(item: T) {
        pushStack.push(item)
    }

    override fun remove(): T {
        movePushToPop()

        return popStack.pop()
    }

    override fun peek(): T {
        movePushToPop()

        return popStack.peek()
    }

    override fun isEmpty(): Boolean
            = pushStack.isEmpty() && popStack.isEmpty()

    private fun movePushToPop() {
        if (popStack.isEmpty()) {
            while (!pushStack.empty()) {
                popStack.push(pushStack.pop())
            }
        }
    }
}

fun main(args: Array<String>) {
    val queue = StackQueue<Int>()

    queue.add(1)
    queue.add(2)
    queue.add(3)
    queue.add(4)

    require(1 == queue.remove())

    queue.add(5)

    require(2 == queue.remove())
    require(3 == queue.remove())
    require(4 == queue.remove())
    require(5 == queue.remove())
}
