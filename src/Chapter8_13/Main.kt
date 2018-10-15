package Chapter8_13

import kotlin.math.max

fun main(args: Array<String>) {
    val boxes= listOf(Box(10, 10, 10), Box(5,5,5))
    require(findMaxStack(boxes) == 15)
}

data class Box(val width: Int, val height: Int, val depth: Int) {
    companion object {
        fun byHeight(): Comparator<Box>
            = Comparator.comparingInt { box -> box.height }
    }

    fun canBeAbove(other: Box): Boolean
        = (
            width  <= other.width  &&
            height <= other.height &&
            depth  <= other.depth
          )
}

fun findMaxStack(boxes: List<Box>): Int {
    val sortedBoxes = boxes
        .asSequence()
        .sortedWith(Box.byHeight().reversed())
        .toList()

    var maxHeight = 0

    for (i in 0 until sortedBoxes.size) {
        val stackHeight = findMaxStackHeight(sortedBoxes.slice(i until boxes.size))
        maxHeight = max(stackHeight, maxHeight)
    }

    return maxHeight
}

fun findMaxStackHeight(boxes: List<Box>): Int {
    var maxHeight = 0
    val bottom = boxes.first()

    for (boxIdx in 1 until boxes.size) {
        val box = boxes[boxIdx]

        if (box.canBeAbove(bottom)) {
            val height = findMaxStackHeight(
                boxes.slice(boxIdx until boxes.size)
            )

            maxHeight = max(maxHeight, height)
        }
    }

    return maxHeight + bottom.height
}
