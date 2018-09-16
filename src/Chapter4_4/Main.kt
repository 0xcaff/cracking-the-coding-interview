package Chapter4_4

import Chapter4_2.BinaryTreeNode
import kotlin.math.absoluteValue

fun main(args: Array<String>)
    = test()

fun test() {
    val twentyTwo = BinaryTreeNode(22)

    val nine = BinaryTreeNode(9, twentyTwo)
    val eighteen = BinaryTreeNode(18)
    val three = BinaryTreeNode(3, nine, eighteen)

    val seven = BinaryTreeNode(7)

    val twenty = BinaryTreeNode(20, three, seven)

    val twelve = BinaryTreeNode(12)
    val five = BinaryTreeNode(value = 5, right = twelve)

    val root = BinaryTreeNode(10, five, twenty)

    require(getBalancingForSubtree(root) is Unbalanced)
    require(getBalancingForSubtree(five) is Balanced)
    require(getBalancingForSubtree(twenty) is Unbalanced)
    require(getBalancingForSubtree(three) is Unbalanced)
}


sealed class BalancedResult
data class Balanced(val depth: Int) : BalancedResult()
object Unbalanced : BalancedResult()

fun getBalancingForSubtree(root: BinaryTreeNode?): BalancedResult {
    if (root == null) {
        return Balanced(0)
    }

    val lhs = getBalancingForSubtree(root.left)
    val rhs = getBalancingForSubtree(root.right)

    if (lhs is Unbalanced || rhs is Unbalanced) {
        return Unbalanced
    }

    val lhsDepth = (lhs as Balanced).depth
    val rhsDepth = (rhs as Balanced).depth

    if ((lhsDepth - rhsDepth).absoluteValue > 1) {
        return Unbalanced
    }

    return Balanced(maxOf(lhsDepth, rhsDepth))
}
