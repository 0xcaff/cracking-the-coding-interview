package Chapter4_6

class BinaryTreeNode(
        val value: Int,
        val left: BinaryTreeNode? = null,
        val right: BinaryTreeNode? = null
) {
    var parent: BinaryTreeNode? = null

    init {
        left?.parent = this
        right?.parent = this
    }

    fun isLeftChild(): Boolean
        = this.parent!!.left == this

    fun isRightChild(): Boolean
        = this.parent!!.right == this
}
