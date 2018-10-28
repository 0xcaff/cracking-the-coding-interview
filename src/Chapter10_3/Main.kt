package Chapter10_3

fun main(args: Array<String>) {
    testFindPivotIdx()

    require(findRotated(intArrayOf(15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14), 5) == 8)
}

fun findRotated(arr: IntArray, value: Int): Int {
    val pivotIdx = findPivotIdx(arr)

    if (pivotIdx == -1) {
        return arr.binarySearch(value)
    }

    val pivot = arr[pivotIdx]
    if (value == pivot) {
        return pivotIdx
    }

    return if (value < arr[0]) {
        arr.binarySearch(value, pivotIdx + 1)
    } else {
        arr.binarySearch(value, 0, pivotIdx - 1)
    }
}


fun testFindPivotIdx() {
    require(findPivotIdx(intArrayOf(15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14)) == 4)
    require(findPivotIdx(intArrayOf(2, 3, 4, 5, 1)) == 3)
    require(findPivotIdx(intArrayOf(3, 4, 5, 1, 2)) == 2)
    require(findPivotIdx(intArrayOf(3, 4, 3, 3, 3)) == 1)
    require(findPivotIdx(intArrayOf(3, 3, 3, 4, 3)) == 3)
    require(findPivotIdx(intArrayOf(3, 3, 3)) == -1)
    require(findPivotIdx(intArrayOf(1, 2, 3, 4)) == -1)
    require(findPivotIdx(intArrayOf(4, 1, 2, 3)) == 0)
}

fun findPivotIdx(arr: IntArray, startIdx: Int = 0, endIdx: Int = arr.size - 1): Int {
    if (startIdx >= endIdx) {
        return -1
    }

    val middleIdx = (startIdx + endIdx) / 2
    if (middleIdx < endIdx && arr[middleIdx] > arr[middleIdx + 1]) {
        return middleIdx
    }

    if (middleIdx > startIdx && arr[middleIdx - 1] > arr[middleIdx]) {
        return middleIdx - 1
    }

    if (arr[startIdx] >= arr[middleIdx]) {
        val pivot = findPivotIdx(arr, startIdx, middleIdx - 1)
        if (pivot != -1) {
            return pivot
        }
    }

    return findPivotIdx(arr, middleIdx + 1, endIdx)
}