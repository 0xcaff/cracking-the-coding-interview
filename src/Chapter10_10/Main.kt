package Chapter10_10

class BSTNode(
    val value: Int,
    var less: BSTNode? = null,
    var more: BSTNode? = null,
    var lessCount: Int = 0
) {
    fun getRank(value: Int): Int? {
        return when {
            value == this.value -> lessCount
            value < this.value -> less?.getRank(value)
            value > this.value -> more?.getRank(value).map { this.lessCount + 1 + it }
            else -> throw IllegalStateException("bad state")
        }
    }

    fun insert(value: Int): BSTNode {
        when {
            value <= this.value -> {
                this.less = this.less?.insert(value) ?: BSTNode(value)
                this.lessCount++
            }
            value > this.value -> {
                this.more = this.more?.insert(value) ?: BSTNode(value)
            }
        }

        return this
    }
}

private fun Int?.map(mapFn: (Int) -> Int): Int? =
    when (this) {
        null -> null
        else -> mapFn(this)
    }

fun main(args: Array<String>) {
    val ints = intArrayOf(5, 1, 4, 4, 5, 9, 7, 13, 3)
    val root = BSTNode(ints[0])

    for (i in ints.sliceArray(1 until ints.size)) {
        root.insert(i)
    }

    require(root.getRank(1) == 0)
    require(root.getRank(3) == 1)
    require(root.getRank(4) == 3)
    require(root.getRank(20) == null)
}
