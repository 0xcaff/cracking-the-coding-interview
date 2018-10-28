package Chapter10_5

fun main(args: Array<String>) {
    val s = arrayOf(
            "at",
            "",
            "",
            "",
            "ball",
            "",
            "",
            "car",
            "",
            "",
            "dad",
            "",
            ""
    )

    require(sparseSearch(s, "ball") == 4)
    require(sparseSearch(s, "dags") == -1)
}

fun sparseSearch(sorted: Array<String>, value: String): Int {
    val midIdx = sorted.size / 2

    val leftIdx = sorted.getNonEmptyIdxLeft(midIdx)
    if (leftIdx != -1) {
        when {
            sorted[leftIdx] == value ->
                return leftIdx

            value < sorted[leftIdx] ->
                return sparseSearch(sorted.sliceArray(0 until leftIdx), value)
        }
    }

    val rightIdx = sorted.getNonEmptyIdxRight(midIdx)
    if (rightIdx != -1) {
        when {
            sorted[rightIdx] == value ->
                return rightIdx

            value > sorted[rightIdx] ->
                return sparseSearch(sorted.sliceArray(rightIdx + 1 until sorted.size), value)
        }
    }

    return -1
}

fun Array<String>.getNonEmptyIdx(startingIdx: Int, diff: Int): Int {
    var idx = startingIdx
    while (idx >= 0 && idx < this.size) {
        if (!this[idx].isEmpty()) {
            return idx
        }

        idx += diff
    }

    return -1
}

fun Array<String>.getNonEmptyIdxLeft(startingIdx: Int): Int = this.getNonEmptyIdx(startingIdx, -1)

fun Array<String>.getNonEmptyIdxRight(startingIdx: Int): Int = this.getNonEmptyIdx(startingIdx, 1)
