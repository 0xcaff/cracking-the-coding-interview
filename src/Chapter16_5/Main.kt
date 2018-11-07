package Chapter16_5

import kotlin.math.max

fun main(args: Array<String>) {
    require(factorsOf(100, 10) == 2)
    require(factorsOf(100000, 10) == 5)
    require(factorsOf(35, 5) == 1)
    require(factorsOf(50, 5) == 2)

    require(factorialZeros(20) == 4)
    require(factorialZeros(25) == 6)
    require(factorialZeros(30) == 7)
    require(factorialZeros(35) == 8)
    require(factorialZeros(40) == 9)
    require(factorialZeros(50) == 12)
    require(factorialZeros(60) == 14)

    require(factorialZeros(10) == 2)
    require(factorialZeros(100) == 24)
    require(factorialZeros(1000) == 249)
    require(factorialZeros(10000) == 2499)
}

/**
 * Write an algorithm which computes the number of trailing zeros in n
 * factorial.
 */
fun factorialZeros(n: Int): Int {
    var zeros = 0

    // n! = 10^z * d or z zeros. That is, there is one zero for each time 2
    // and 5 is multiplied together or a multiple of 10 is used.

    for (i in 1..n) {
        val zerosFromTen = factorsOf(i, 10)
        val zerosFromFive = factorsOf(i, 5)

        zeros += max(zerosFromTen, zerosFromFive)
    }

    return zeros
}

fun factorsOf(n: Int, of: Int): Int {
    if (n % of != 0) {
        return 0
    }

    return 1 + factorsOf(n / of, of)
}
