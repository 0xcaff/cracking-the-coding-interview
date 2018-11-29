/**
 * Kth Multiple: Design an algorithm to find the kth number such that the only
 * prime factors are 3, 5, and 7. Note that 3, 5, and 7 do not have to be
 * factors, but it should not have any other prime factors.
 */
package Chapter17_9

import java.util.*

fun main(args: Array<String>) {
    require(kthMultiple(1) == 1)   // 3^0 * 5^0 * 7^0

    require(kthMultiple(2) == 3)   // 3^1 * 5^0 * 7^0
    require(kthMultiple(3) == 5)   // 3^0 * 5^1 * 7^0
    require(kthMultiple(4) == 7)   // 3^0 * 5^0 * 7^1

    require(kthMultiple(5) == 9)   // 3^2 * 5^0 * 7^0
    require(kthMultiple(6) == 15)  // 3^1 * 5^1 * 7^0
    require(kthMultiple(7) == 21)  // 3^1 * 5^0 * 7^1

    require(kthMultiple(8) == 25)  // 3^0 * 5^2 * 7^0
    require(kthMultiple(9) == 27)  // 3^3 * 5^0 * 7^0
    require(kthMultiple(10) == 35) // 3^0 * 5^1 * 7^1
    require(kthMultiple(11) == 45) // 3^2 * 5^1 * 7^0
    require(kthMultiple(12) == 49) // 3^0 * 5^0 * 7^2
    require(kthMultiple(13) == 63) // 3^2 * 5^0 * 7^1
}

fun kthMultiple(k: Int): Int {
    var value = 0

    val queue3 = LinkedList<Int>()
    val queue5 = LinkedList<Int>()
    val queue7 = LinkedList<Int>()
    queue3.add(1)

    for (iteration in 0 until k) {
        val top3 = queue3.getOrElse(0) { Int.MAX_VALUE }
        val top5 = queue5.getOrElse(0) { Int.MAX_VALUE }
        val top7 = queue7.getOrElse(0) { Int.MAX_VALUE }

        value = sequenceOf(top3, top5, top7).min()!!

        when (value) {
            top3 -> {
                queue3.remove()
                queue3.add(3 * value)
                queue5.add(5 * value)
                queue7.add(7 * value)
            }
            top5 -> {
                queue5.remove()
                queue5.add(5 * value)
                queue7.add(7 * value)
            }
            top7 -> {
                queue7.remove()
                queue7.add(7 * value)
            }
        }
    }

    return value
}

