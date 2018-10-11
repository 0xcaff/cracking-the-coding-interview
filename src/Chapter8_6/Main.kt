package Chapter8_6

import java.util.*

class IncreasingStack : Stack<Int>() {
    override fun push(item: Int?): Int {
        val top = if (this.size == 0) { null } else { this.peek() }
        require(top == null || item != null && item < top)
        return super.push(item)
    }

    fun deepCopy(): IncreasingStack {
        val new = IncreasingStack()
        new.addAll(this)

        return new
    }
}

class Towers(
        val startPeg: IncreasingStack = IncreasingStack(),
        val endPeg: IncreasingStack = IncreasingStack(),
        val auxiliaryPeg: IncreasingStack = IncreasingStack()
) {
    companion object {
        fun withN(n: Int): Towers {
            val firstPeg = IncreasingStack()
            for (i in (1..n).reversed()) {
                firstPeg.push(i)
            }

            return Towers(firstPeg)
        }
    }

    override fun toString(): String {
        val tallestPegHeight = maxOf(startPeg.size, endPeg.size, auxiliaryPeg.size)
        var output = ""

        for (i in (0 until tallestPegHeight).reversed()) {
            output += startPeg.getOrNull(i) ?: " "
            output += " "

            output += endPeg.getOrNull(i) ?: " "
            output += " "

            output += auxiliaryPeg.getOrNull(i) ?: " "
            output += '\n'
        }

        return output
    }

    private fun deepCopy(): Towers
        = Towers(startPeg.deepCopy(), endPeg.deepCopy(), auxiliaryPeg.deepCopy())

    fun movePegsFromStartToEnd(): List<Towers> {
        val towers = mutableListOf(this.deepCopy())

        val movementTowers = moveDisks(startPeg.size, startPeg, endPeg, auxiliaryPeg)
        towers.addAll(movementTowers)

        return towers
    }
}

fun moveDisks(n: Int, startPeg: IncreasingStack, endPeg: IncreasingStack, auxiliaryPeg: IncreasingStack): List<Towers> {
    val towers = mutableListOf<Towers>()

    if (n > 1) {
        // Move All But Last to Auxiliary Peg
        val toAuxTowers = moveDisks(n - 1, startPeg, auxiliaryPeg, endPeg)
        towers.addAll(toAuxTowers.map {
            Towers(
                startPeg = it.startPeg,
                endPeg = it.auxiliaryPeg,
                auxiliaryPeg = it.endPeg
            )
        })
    }

    // Move Last to Destination
    endPeg.push(startPeg.pop())
    towers.add(Towers(startPeg.deepCopy(), endPeg.deepCopy(), auxiliaryPeg.deepCopy()))

    if (n > 1) {
        // Move Everything From Auxiliary Peg to Destination Peg
        val fromAuxTowers = moveDisks(n - 1, auxiliaryPeg, endPeg, startPeg)
        towers.addAll(fromAuxTowers.map {
            Towers(
                startPeg = it.auxiliaryPeg,
                endPeg = it.endPeg,
                auxiliaryPeg = it.startPeg
            )
        })
    }

    return towers
}

fun main(args: Array<String>) {
    println(Towers.withN(4).movePegsFromStartToEnd().joinToString("\n\n"))

    require(Towers.withN(1).movePegsFromStartToEnd().size == 1 + 1)
    require(Towers.withN(2).movePegsFromStartToEnd().size == 3 + 1)
    require(Towers.withN(3).movePegsFromStartToEnd().size == 7 + 1)
    require(Towers.withN(4).movePegsFromStartToEnd().size == 15 + 1)
    require(Towers.withN(5).movePegsFromStartToEnd().size == 31 + 1)
}

// n = 1, steps = 1

// 1

//   1

// n = 2, steps = 3

// 1
// 2

// 2   1

//   2 1

//   1
//   2

// n = 3, steps = 7

// 1
// 2
// 3

// 2
// 3 1

// 3 1 2

//     1
// 3   2

//     1
//   3 2

// 1 3 2

//   2
// 1 3

// 1
// 2
// 3

// n = 4, steps = 15

// 1
// 2
// 3
// 4

// 2
// 3
// 4   1

// 3
// 4 2 1

// 3 1
// 4 2

//   1
// 4 2 3

// 1
// 4 2 3

// 1   2
// 4   3

//     1
//     2
// 4   3

//     1
//     2
//   4 3

//   1 2
//   4 3

//   1
// 2 4 3

// 1
// 2 4 3

// 1 3
// 2 4

//
//   3
// 2 4 1

//   2
//   3
//   4 1

//   1
//   2
//   3
//   4
