package Chapter10_1

fun main(args: Array<String>) {
    val a = intArrayOf(1, 3, 5, 9, 9, 9, 9, 9)
    val b = intArrayOf(2, 3, 4, 6, 7)

    mergeInPlace(a, b)

    require(a.contentEquals(intArrayOf(1, 2, 3, 3, 4, 5, 6, 7)))
}

/**
 * Given two sorted arrays where a has a large enough buffer at the end to hold
 * B. Merges B into A in sorted order.
 */
fun mergeInPlace(a: IntArray, b: IntArray) {
    var bIdx = 0
    var aIdx = 0

    while (aIdx < a.size && bIdx < b.size) {
        while (a[aIdx] < b[bIdx]) aIdx++

        a.insert(aIdx, b[bIdx])
        bIdx++
    }
}

fun IntArray.insert(idx: Int, value: Int) {
    for (i in (idx until (this.size-1)).reversed()) {
        this[i + 1] = this[i]
    }

    this[idx] = value
}