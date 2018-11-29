/**
 * Shuffle: Write a method to shuffle a deck of cards. It must be a perfect
 * shuffle. In other words, each of the 52! permutations of the deck has to be
 * equally likely. Assume that you are given a random number generator which is
 * perfect.
 */
package Chapter17_2

import java.util.*

fun main(args: Array<String>) {
    val unshuffledList = (1..52).toList().toIntArray()
    val list: IntArray = unshuffledList.clone()
    shuffle(list)
    require(!list.contentEquals(unshuffledList))
}

/*
Ideas:
* For Each Position, Take From Unsorted After. The probability of getting a
  certain combo is 1 in 52 * 51 * 50 ... = 52! This is fisher yates I think.
 */
fun shuffle(list: IntArray) {
    for (idx in 0..list.lastIndex) {
        list.swap(idx, (idx..list.lastIndex).random())
    }
}

fun IntRange.random() =
    Random().nextInt((endInclusive + 1) - start) +  start

fun IntArray.swap(aIdx: Int, bIdx: Int) {
    val tmp = this[aIdx]
    this[aIdx] = this[bIdx]
    this[bIdx] = tmp
}
