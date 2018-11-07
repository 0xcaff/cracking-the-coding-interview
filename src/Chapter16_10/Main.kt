package Chapter16_10

fun main(args: Array<String>) {
    val lifetimes = arrayOf(
            Lifetime(1962, 2012),
            Lifetime(1899, 1924),
            Lifetime(1923, 2018)
    )

    require(mostPeopleAlive(lifetimes) == 1923)
}


data class Lifetime(val birthYear: Int, val deathYear: Int)

/**
 * Living People: Given a list of people with their birth and death years,
 * implement a method to compute the year with the most number of people alive.
 * You may assume that all people were born between 1900 and 2000 (inclusive).
 * If a person was alive during any portion of that year, they should be
 * included in that years count. For example, Person (birth = 1908, death =
 * 1909) is included in the counts for both 1908 and 1909.
 */
fun mostPeopleAlive(lifetimes: Array<Lifetime>): Int {
    /*
    Ideas:
        Sort and count. O(n log n)
        Constant Array Count (Discrete Values) O(n * 100)
     */

    val peopleAlivePerYear = IntArray(2000 - 1900 + 1)

    for (lifetime in lifetimes) {
        for (year in Math.max(1900, lifetime.birthYear)..Math.min(lifetime.deathYear, 2000)) {
            peopleAlivePerYear[year - 1900]++
        }
    }

    return peopleAlivePerYear.withIndex().maxBy { it.value }!!.index + 1900
}
