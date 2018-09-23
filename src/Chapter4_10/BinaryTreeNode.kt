package Chapter4_10

import java.util.*

class BinaryTreeNode(private val parent: BinaryTreeNode? = null) {
    val left: WeightedTree = WeightedTree()
    val right: WeightedTree = WeightedTree()

    fun getRandom(getBetween: (range: IntRange) -> Int = { range -> range.random() }): BinaryTreeNode {
        val rand = getBetween(0 until left.weight + right.weight + 1)
        return when (rand) {
            in (0 until left.weight) -> left.child!!.getRandom(getBetween)
            in (left.weight until right.weight + left.weight) -> right.child!!.getRandom(getBetween)
            else -> this
        }
    }

    fun addLeftChild(newLeftChild: BinaryTreeNode) {
        require(newLeftChild.parent == this)
        left.addChild(newLeftChild)
        updateParents()
    }

    fun addRightChild(newRightChild: BinaryTreeNode) {
        require(newRightChild.parent == this)
        right.addChild(newRightChild)
        updateParents()
    }

    private fun updateParents() {
        if (parent == null) {
            return
        }

        when {
            this == parent.right.child -> parent.right.weight++
            this == parent.left.child -> parent.left.weight++
        }

        return parent.updateParents()
    }
}

class WeightedTree {
    var weight: Int = 0

    var child: BinaryTreeNode? = null
        private set

    fun addChild(binaryTreeNode: BinaryTreeNode) {
        child = binaryTreeNode
        weight++
    }
}

fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) + start

