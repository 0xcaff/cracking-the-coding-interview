package Chapter10_11

fun main(args: Array<String>) {
    val array = intArrayOf(5, 3, 1, 2, 3)
    makeAlternating(array)

    require(array.contentEquals(intArrayOf(5, 1, 3, 2, 3)))
}

fun makeAlternating(input: IntArray) {
    for (i in (0..input.size).step(2)) {
        if (i > 0 && input[i - 1] > input[i]) {
            input.swap(i - 1, i)
        }

        if (i < input.size - 1 && input[i] < input[i + 1]) {
            input.swap(i, i + 1)
        }
    }
}

fun IntArray.swap(fromIdx: Int, toIdx: Int) {
    val temp = this[fromIdx]
    this[fromIdx] = this[toIdx]
    this[toIdx] = temp
}

