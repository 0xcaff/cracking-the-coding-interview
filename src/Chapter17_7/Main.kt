package Chapter17_7

fun main(args: Array<String>) {
    caseFromProblem()
    caseFromSolution()
}

fun caseFromProblem() {
    val names = mapOf(
            Pair("John", 15),
            Pair("Jon", 12),
            Pair("Chris", 13),
            Pair("Kris", 4),
            Pair("Christopher", 19)
    )

    val synonyms = listOf(
            Pair("Jon", "John"),
            Pair("John", "Johnny"),
            Pair("Chris", "Kris"),
            Pair("Chris", "Christopher")
    )

    val translated = babyNames(names, synonyms)
    println(translated)
}

fun caseFromSolution() {
    val names = mapOf(
            Pair("John", 10),
            Pair("Jon", 3),
            Pair("Davis", 2),
            Pair("Kari", 3),
            Pair("Johnny", 11),
            Pair("Carlton", 8),
            Pair("Carleton", 2),
            Pair("Jonathan", 9),
            Pair("Carrie", 5)
    )

    val synonyms = listOf(
            Pair("Jonathan", "John"),
            Pair("Jon", "Johnny"),
            Pair("Johnny", "John"),
            Pair("Kari", "Carrie"),
            Pair("Carleton", "Carlton")
    )

    val translated = babyNames(names, synonyms)
    println(translated)
}

fun makeIndexByName(names: List<String>): Map<String, Int> {
    val map = mutableMapOf<String, Int>()

    for (idx in names.indices) {
        val name = names[idx]
        map[name] = idx
    }

    return map
}

fun babyNames(names: Map<String, Int>, synonyms: List<Pair<String, String>>): Map<String, Int> {
    val sortedNames = names.keys.toList()
    val nameByIndex = makeIndexByName(sortedNames)
    val unionFind = UnionFind(sortedNames.size)

    for ((lhs, rhs) in synonyms) {
        val lhsIdx = nameByIndex[lhs] ?: continue
        val rhsIdx = nameByIndex[rhs] ?: continue

        unionFind.union(lhsIdx, rhsIdx)
    }

    val output = mutableMapOf<String, Int>()
    for (idx in sortedNames.indices) {
        val rootIdx = unionFind.findRoot(idx)

        output.compute(sortedNames[rootIdx]) { _, value ->
            (value ?: 0) + names[sortedNames[idx]]!!
        }
    }

    return output
}

class Element(var parentIdx: Int) {
    var rank = 0
}

class UnionFind(size: Int) {
    private val elements: Array<Element> = Array(size) { Element(it) }

    fun findRoot(idx: Int): Int {
        val element = elements[idx]
        val isRoot = element.parentIdx == idx

        if (!isRoot) {
            element.parentIdx = findRoot(element.parentIdx)
        }

        return element.parentIdx
    }

    fun union(aIdx: Int, bIdx: Int) {
        val aRootIdx = findRoot(aIdx)
        val bRootIdx = findRoot(bIdx)

        val aRoot = elements[aRootIdx]
        val bRoot = elements[bRootIdx]

        when {
            aRoot.rank < bRoot.rank -> aRoot.parentIdx = bRootIdx
            aRoot.rank > bRoot.rank -> bRoot.parentIdx = aRootIdx
            else -> {
                bRoot.parentIdx = aRootIdx
                aRoot.rank++
            }
        }
    }
}

