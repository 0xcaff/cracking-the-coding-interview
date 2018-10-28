package Chapter10_4

class Listy(private val elements: IntArray) {
    fun elementAt(idx: Int): Int {
        return when {
            idx < elements.size -> elements[idx]
            else -> -1
        }
    }

    fun binarySearchUpTo(value: Int, bound: Int): Int
        = elements.binarySearch(
            value,
            bound / 2,
            bound - 1
        )
}

tailrec fun searchSortedListy(list: Listy, value: Int, bound: Int = 1): Int {
    val atBound = list.elementAt(bound)
    if (value == atBound) {
        return bound
    }

    if (value < atBound) {
        return list.binarySearchUpTo(value, bound)
    }

    return searchSortedListy(list, value, bound * 2)
}
