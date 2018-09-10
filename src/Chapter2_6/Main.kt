package Chapter2_6

import Chapter2_1.LinkedList
import java.util.*

fun main(args: Array<String>) {
    require(test(intArrayOf(1, 2, 1)))
    require(!test(intArrayOf(1, 2, 3)))
    require(test(intArrayOf(1, 2, 2, 1)))
}

fun test(input: IntArray): Boolean = isPalindrome(LinkedList.fromArray(input))

fun isPalindrome(list: LinkedList): Boolean {
    val stack = Stack<Int>()
    for (node in list) {
        stack.push(node.data)
    }

    for (node in list) {
        if (node.data != stack.pop()) {
            return false
        }
    }

    return true
}
