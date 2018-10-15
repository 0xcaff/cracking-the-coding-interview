package Chapter8_11

fun main(args: Array<String>) {
    require(coins(1) == 1)
    require(coins(5) == 2)
    require(coins(10) == 4)
}

fun coins(amount: Int): Int {
    var total = 0

    val maxQuarters = amount / 25
    for (i in 0..maxQuarters) {
        val remaining = amount - i * 25
        total += coinsWithoutQuarters(remaining)
    }

    return total
}

fun coinsWithoutQuarters(amount: Int): Int {
    var total = 0

    val maxDimes = amount / 10
    for (i in 0..maxDimes) {
        val remaining = amount - i * 10
        total += coinsWithoutDimes(remaining)
    }

    return total
}

fun coinsWithoutDimes(amount: Int): Int {
    var total = 0

    val maxNickels = amount / 5
    for (i in 0..maxNickels) {
        total += 1
    }

    return total
}
