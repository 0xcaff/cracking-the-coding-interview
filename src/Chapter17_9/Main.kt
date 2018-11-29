/**
 * Circus Tower: A circus is designing a tower routine consisting of people
 * standing atop one another's shoulders. For practical and aesthetic reasons,
 * each person must be both shorter and lighter than the person below him or
 * her. Given the heights and weights of each person in the circus, write a
 * method to compute the largest possible number of people in such a tower.
 */
package Chapter17_9

data class Person(val height: Int, val weight: Int) {
    fun canStandOn(other: Person): Boolean = height < other.height && weight < other.weight
}

fun main(args: Array<String>) {
    val people = arrayOf(
            Person(65, 100),
            Person(70, 150),
            Person(56, 90),
            Person(75, 190),
            Person(60, 95),
            Person(68, 110)
    )

    require(circusTowerDynamicProgramming(people) == 6)
}

fun circusTowerRecursive(people: Array<Person>): Int {
    people.sortByDescending(Person::height)

    var max = 0
    for (idx in people.indices) {
        max = Math.max(
          max,
          circusTowerRecursiveHelper(people.sliceArray(idx..people.lastIndex))
        )
    }

    return max
}

fun circusTowerRecursiveHelper(sortedPeople: Array<Person>): Int {
    var maxHeight = 0
    val bottom = sortedPeople.first()

    for (idx in 1..sortedPeople.lastIndex) {
        val person = sortedPeople[idx]

        if (person.canStandOn(bottom)) {
            maxHeight = Math.max(
              maxHeight,
              circusTowerRecursiveHelper(
                sortedPeople.sliceArray(idx..sortedPeople.lastIndex)
              )
            )
        }
    }

    return maxHeight + 1
}

fun circusTowerDynamicProgramming(people: Array<Person>): Int {
    people.sortByDescending(Person::height)

    val longestSubsequenceEndingAt = IntArray(people.size)
    longestSubsequenceEndingAt[0] = 1
    for (endIdx in 1..people.lastIndex) {
        var max = 0

        for (startIdx in 0..endIdx) {
            if (people[endIdx].canStandOn(people[startIdx])) {
                max = Math.max(max, longestSubsequenceEndingAt[startIdx])
            }
        }

        longestSubsequenceEndingAt[endIdx] = max + 1
    }

    return longestSubsequenceEndingAt.max()!!
}
