package Chapter8_9

fun main(args: Array<String>) {
    require(matchedParens(1) == listOf("()"))
    require(matchedParens(2) == listOf("()()", "(())"))
    require(matchedParens(3).size == listOf("((()))", "(()())", "(())()", "()(())", "()()()").size)
    require(matchedParens(4).size == 14)
    require(matchedParens(5).size == 42)
}

fun matchedParens(n: Int): List<String> {
    if (n == 0) {
        return listOf("")
    }

    val output = mutableListOf<String>()
    for (lhsParensCount in 0 until n) {
        val rhsParensCount = n - 1 - lhsParensCount

        val lhsParens = matchedParens(lhsParensCount)
        val rhsParens = matchedParens(rhsParensCount)

        for (lhsParen in lhsParens) {
            for (rhsParen in rhsParens) {
                output += "($lhsParen)$rhsParen"
            }
        }
    }

    return output
}
