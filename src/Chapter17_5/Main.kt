/**
 * Letters and Numbers: Given an array filled with letters and numbers, find
 * the longest subarray with an equal number of letters and numbers.
 */
package Chapter17_5

fun main(args: Array<String>) {
    require(lettersAndNumbers(
      "111abc123111".toCharArray()
    ).contentEquals(
      "abc123".toCharArray()
    ))

    require(lettersAndNumbers(
      "aaa12abc123abaaa".toCharArray()
    ).contentEquals(
      "12abc123ab".toCharArray()
    ))
}

fun computeDeltas(chars: CharArray): IntArray {
    val deltas = IntArray(chars.size)
    var delta = 0

    for (idx in chars.indices) {
        val char = chars[idx]
        if (char.isDigit()) {
            delta++
        } else {
            delta--
        }

        deltas[idx] = delta
    }

    return deltas
}

fun lettersAndNumbers(charArray: CharArray): CharArray {
    val differences = computeDeltas(charArray)
    val firstDifference = mutableMapOf<Int, Int>()

    var maxRange = (0..0)
    for (idx in differences.indices) {
        val firstDiffIdx = firstDifference.getOrPut(differences[idx]) { idx }
        if (firstDiffIdx != idx) {
            val possibleRange = (firstDiffIdx..idx)

            if (possibleRange.size() >= maxRange.size()){
                maxRange = possibleRange
            }
        }
    }

    return charArray.sliceArray((maxRange.start + 1..maxRange.endInclusive))
}

fun IntRange.size()
    = endInclusive - start

/*
Ideas:
  * Brute Force. (n choose 2) subarrays. O((n choose 2)) = O(n^2)
  * Information Reuse Possible?
    * The resulting subarray must be even length.
    * The subarray can always be grown by adding a letter and a number.
    * DP O(n) time possible
    * The letter and the number might not be immediate (abc123). This is a balancing problem.
  * Greedy Balancing Algorithm
    * Turn Input Into Array of Types
    * Merge Neighboring Types
    * Merge Balanced
    * Find Longest Balanced
    * Doesn't work because 12abc123ab -> DDLLLDDDLL -> 2D 3L 3D 2L -> 2D 3B 2L
  * Because only Letters and Digits in Array
    * When the number of digits is equal to the number of letters 0..(num digits + num letters) is valid.
    *
 */

/*
fun lettersAndNumbers(arr: CharArray): CharArray {
    val types = arr.map { it.type() }
    val ranges = mutableListOf<TypedRange>()

    for (idx in types.indices) {
        val type = types[idx]
        val lastRange = ranges.lastOrNull()

        if (lastRange == null || lastRange.type != type) {
            ranges.add(TypedRange(type, (idx..idx)))
        } else {
            lastRange.idxRange = lastRange.idxRange.addOne()
        }
    }

    val mergedRanges =
    for (idx in ranges.indices) {
        val range = types[idx]
        val lastRange =
    }
}

fun IntRange.addOne(): IntRange
    = (start..(endInclusive + 1))
*/

/*
fun lettersAndNumbers(arr: CharArray): CharArray {
    var longestSubarray = charArrayOf()
    var currentSubarray = charArrayOf()

    for (startIdx in (1..2)) {
        for (idx in (startIdx..arr.lastIndex step 2)) {
            val last = arr[idx].type()
            val lastLast = arr[idx - 1].type()

            if (last != Other && lastLast != Other && last != lastLast) {
                // Can Grow Array
                currentSubarray += charArrayOf(arr[idx], arr[idx - 1])
            } else {
                if (currentSubarray.size > longestSubarray.size) {
                    longestSubarray = currentSubarray
                }

                currentSubarray = charArrayOf()
            }
        }

        if (currentSubarray.size > longestSubarray.size) {
            longestSubarray = currentSubarray
        }
    }

    return longestSubarray
}
*/

/*
data class TypedRange(val type: Type, var idxRange: IntRange)

sealed class Type
object Digit : Type()
object Letter : Type()

fun Char.type(): Type =
    when {
        isDigit() -> Digit
        isLetter() -> Letter
        else -> throw IllegalArgumentException()
    }
    */
