package Chapter8_3

fun main(args: Array<String>) {
    require(findMagicIndex(intArrayOf(0, 1, 2, 3, 4)) == 2)
    require(findMagicIndex(intArrayOf(0, 10, 20, 30, 40)) == 0)
    require(findMagicIndex(intArrayOf(-1, 0, 1, 2, 4)) == 4)
    require(findMagicIndex(intArrayOf(1, 2, 3, 4, 5)) == null)
}

fun findMagicIndex(array: IntArray, startingIdx: Int = 0): Int? {
    if (array.isEmpty()) {
        return null
    }

    val middleIdx = array.size / 2
    val middle = array[middleIdx]

    val compareIdx = middleIdx + startingIdx

    return when {
        middle > compareIdx -> {
            val left = array.sliceArray(0 until middleIdx)

            findMagicIndex(left, startingIdx)
        }
        middle < compareIdx -> {
            val right = array.sliceArray((middleIdx + 1) until array.size)

            findMagicIndex(right, middleIdx + 1)
        }
        else -> compareIdx
    }
}
