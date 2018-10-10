package Chapter8_5

fun main(args: Array<String>) {
    require(recursiveMultiply(10, 10) == 10 * 10)
    require(recursiveMultiply(5, 3) == 5 * 3)
    require(recursiveMultiply(5, 4) == 5 * 4)
}

fun recursiveMultiply(a: Int, b: Int): Int {
    if (b == 0) {
        return 0
    }

    var output = 0

    val div = b / 2
    val isOdd = b % 2 == 1

    val half = recursiveMultiply(a, div)
    output += half + half

    if (isOdd) {
        output += a
    }

    return output
}
