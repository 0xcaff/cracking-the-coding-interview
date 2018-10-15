package Chapter8_14

import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    require(countEval("1^0|0|1", false) == 2)
    require(countEval("0&0&0&1^1|0", true) == 10)
}

fun countEval(string: String, expected: Boolean): Int {
    return when {
        string.isEmpty() -> 0
        string.length == 1 -> {
            when (toBoolean(string)) {
                expected -> 1
                else -> 0
            }
        }
        else -> {
            var ways = 0

            for (i in 1 until string.length step 2) {
                val leftOperands = string.slice(0 until i)
                val rightOperands = string.slice(i + 1 until string.length)
                val operator = string[i]

                val leftTrue = countEval(leftOperands, true)
                val leftFalse = countEval(leftOperands, false)
                val rightTrue = countEval(rightOperands, true)
                val rightFalse = countEval(rightOperands, false)

                val tf = leftTrue * rightFalse
                val tt = leftTrue * rightTrue
                val ft = leftFalse * rightTrue
                val ff = leftFalse * rightFalse

                val combined = when {
                    operator == '^' && expected -> tf + ft
                    operator == '^' && !expected -> tt + ff
                    operator == '&' && expected -> tt
                    operator == '&' && !expected -> tf + ft + ff
                    operator == '|' && expected -> tf + tt + ft
                    operator == '|' && !expected -> ff
                    else -> throw IllegalArgumentException("unexpected input")
                }

                ways += combined
            }

            ways
        }
    }
}

fun toBoolean(s: String): Boolean
    = s == "1"
