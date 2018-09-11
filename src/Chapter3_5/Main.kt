package Chapter3_5

import java.util.*

fun main(args: Array<String>) {
    val input = intArrayOf(34, 3, 31, 98, 92, 23)
    val sorted = intArrayOf(3, 23, 31, 34, 92, 98)
    val inputStack = Stack<Int>()

    for (i in input) {
        inputStack.push(i)
    }

    val sortedStack = sortStack(inputStack)

    for (i in sorted) {
        require(sortedStack.pop() == i)
    }
}

fun sortStack(input: Stack<Int>): Stack<Int> {
    val sortedOutput = Stack<Int>()

    while (!input.empty()) {
        val smallestValue = input.pop()

        while (!sortedOutput.empty() && sortedOutput.peek() < smallestValue) {
            input.push(sortedOutput.pop())
        }

        sortedOutput.push(smallestValue)
    }

    return sortedOutput
}
