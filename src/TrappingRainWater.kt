// https://leetcode.com/problems/trapping-rain-water
fun main(args: Array<String>) {
    val heights = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
    val peaks = booleanArrayOf(false, true, false, true, false, false, false, true, false, false, true, false)
    val peaksLeftOf = intArrayOf(-1, -1, 1, 1, 3, 3, 3, 3, 7, 7, 7, 7)
    val peaksRightOf = intArrayOf(7, 7, 7, 7, 7, 7, 7, 10, 10, 10, -1, -1)

    require(findPeaks(heights).contentEquals(peaks))
    require(findTallestPeakLeftOf(heights, peaks).contentEquals(peaksLeftOf))
    require(findTallestPeakRightOf(heights, peaks).contentEquals(peaksRightOf))
    require(trap(heights) == 6)

    require(trap(intArrayOf(0, 1, 0)) == 0)
    require(trap(intArrayOf(1, 0, 1)) == 1)
    require(trap(intArrayOf(1, 0, 0)) == 0)
    require(trap(intArrayOf(0, 0, 1)) == 0)
    require(trap(intArrayOf(1, 1, 1)) == 0)

    require(trap(intArrayOf(1, 0, 2)) == 1)
    require(trap(intArrayOf(2, 0, 1)) == 1)
    require(trap(intArrayOf(0, 10, 0, 5, 0, 10, 0)) == 10 + 5 + 10)
    require(trap(intArrayOf(10, 0, 5, 0, 10)) == 10 + 5 + 10)
    require(trap(intArrayOf(10, 0, 2, 0, 4, 0, 6, 0, 20)) == 10 * 4 + 8 + 6 + 4)
    require(trap(intArrayOf(10, 0, 2, 0, 4, 0, 6, 0, 20).reversedArray()) == 10 * 4 + 8 + 6 + 4)
    require(trap(intArrayOf(10, 8, 9, 7, 8)) == 2)
    require(trap(intArrayOf(10, 8, 9, 7, 8).reversedArray()) == 2)

    require(trap(intArrayOf(0, 5, 0, 3)) == 3)
}

fun trap(heights: IntArray): Int {
    var totalCollected = 0

    val peaks = findPeaks(heights)
    val peaksLeftOf = findTallestPeakLeftOf(heights, peaks)
    val peaksRightOf = findTallestPeakRightOf(heights, peaks)

    for (i in 0 until heights.size) {
        val tallestPeakLeftOfIdx = peaksLeftOf[i]
        val tallestPeakRightOfIdx = peaksRightOf[i]

        if (tallestPeakLeftOfIdx == -1 || tallestPeakRightOfIdx == -1) {
            continue
        }

        val shortestPeakIdx =
        if (heights[tallestPeakLeftOfIdx] < heights[tallestPeakRightOfIdx]) {
            tallestPeakLeftOfIdx
        } else {
            tallestPeakRightOfIdx
        }

        val toAdd = heights[shortestPeakIdx] - heights[i]
        if (toAdd >= 0) {
            totalCollected += toAdd
        }
    }

    return totalCollected
}

fun findTallestPeakLeftOf(heights: IntArray, peaks: BooleanArray): IntArray {
    val output = IntArray(heights.size)
    var maxPeakIdx = -1

    for (i in 0 until heights.size) {
        output[i] = maxPeakIdx

        val isPeak = peaks[i]
        if (isPeak && (
            (maxPeakIdx == -1) ||
            (maxPeakIdx != -1 && heights[i] >= heights[maxPeakIdx])
        )) {
            maxPeakIdx = i
        }
    }

    return output
}

fun findTallestPeakRightOf(heights: IntArray, peaks: BooleanArray): IntArray {
    val output = IntArray(heights.size)
    var maxPeakIdx = -1

    for (i in heights.size - 1 downTo 0) {
        output[i] = maxPeakIdx

        val isPeak = peaks[i]
        if (isPeak && (
            (maxPeakIdx == -1) ||
            (maxPeakIdx != -1 && heights[i] >= heights[maxPeakIdx])
        )) {
            maxPeakIdx = i
        }
    }

    return output
}

fun isPeakAt(heights: IntArray, idx: Int): Boolean {
    val leftHeight = heights.getOrElse(idx - 1) { Integer.MIN_VALUE }
    val rightHeight = heights.getOrElse(idx + 1) { Integer.MIN_VALUE }
    val centerHeight = heights[idx]

    if (centerHeight == leftHeight && centerHeight == rightHeight) {
        return false
    }

    return centerHeight >= leftHeight && centerHeight >= rightHeight
}

fun findPeaks(heights: IntArray): BooleanArray {
    val output = BooleanArray(heights.size)
    for (i in heights.indices) {
        output[i] = isPeakAt(heights, i)
    }

    return output
}

