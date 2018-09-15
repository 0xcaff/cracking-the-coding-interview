package Chapter4_2

data class BinaryTreeNode(
        val value: Int,
        var left: BinaryTreeNode? = null,
        var right: BinaryTreeNode? = null
)