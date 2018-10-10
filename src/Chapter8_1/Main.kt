package Chapter8_1

fun main(args: Array<String>) {
    require(waysToStepUpTo(1) == 1)
    require(waysToStepUpTo(2) == 2)
    require(waysToStepUpTo(3) == 4)
    require(waysToStepUpTo(4) == 7)
    require(waysToStepUpTo(5) == 13)

    require(waysToStepUpToDP(1) == 1)
    require(waysToStepUpToDP(2) == 2)
    require(waysToStepUpToDP(3) == 4)
    require(waysToStepUpToDP(4) == 7)
    require(waysToStepUpToDP(5) == 13)

    // 1

    // 1, 1
    // 2

    // 1, 1, 1
    // 2, 1
    // 1, 2
    // 3

    // 1, 1, 1, 1
    // 1, 3
    // 3, 1
    // 2, 2
    // 1, 1, 2
    // 2, 1, 1
    // 1, 2, 1
}

fun waysToStepUpTo(n: Int): Int {
    require(n > 0)

    return when (n) {
        1 -> 1
        2 -> 2
        3 -> 4
        else -> (
                // Single Step
                waysToStepUpTo(n - 1) +
                // Hop Two
                waysToStepUpTo(n - 2) +
                // Hop Three
                waysToStepUpTo(n - 3)
                )
    }
}

fun waysToStepUpToDP(n: Int): Int {
    val storage = IntArray(maxOf(n, 3))

    storage[0] = 1
    storage[1] = 2
    storage[2] = 4

    for (i in 3 until n) {
        storage[i] = storage[i - 1] + storage[i - 2] + storage[i - 3]
    }

    return storage[n - 1]
}