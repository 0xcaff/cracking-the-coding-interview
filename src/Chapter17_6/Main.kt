/**
 * Count of 2s: Write a method to count the number of 2s that appear in the
 * numbers between 0 and n inclusive.
 */
package Chapter17_6

fun main(args: Array<String>) {
    require(count2sIn(2) == 1)
    require(count2sIn(22) == 2)
    require(count2sIn(222) == 3)
    require(count2sIn(202) == 2)
    require(count2sIn(282) == 2)
    require(count2sIn(22123) == 3)
    require(count2sIn(112341) == 1)

    require(count2sUntil(25) == 9)
}

tailrec fun count2sIn(num: Int, acc: Int = 0): Int {
    if (num < 10) {
        return if (num == 2) { acc + 1 } else { acc + 0 }
    }

    return count2sIn(
      num / 10,
      acc + if (num % 10 == 2) { 1 } else { 0 }
    )
}

fun count2sUntil(n: Int): Int {
    var count = 0

    for (i in (0..n)) {
        count += count2sIn(i)
    }

    return count
}
