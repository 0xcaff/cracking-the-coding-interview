package Chapter4_1

import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    test()
}

fun test() {
    val root = GraphVertex()
    val first = GraphVertex()
    val second = GraphVertex()
    val third = GraphVertex()
    val fourth = GraphVertex()
    val fifth = GraphVertex()

    root.addNeighbors(first, fourth, fifth)
    first.addNeighbors(third, fourth)
    second.addNeighbors(first)
    third.addNeighbors(second, fourth)

    require(isConnected(root, second))
    require(!isConnected(second, root))
    require(!isConnected(fifth, root))
}

fun isConnected(start: GraphVertex, end: GraphVertex): Boolean
    = breathFirstTraversal(start) { vertex -> vertex == end } != null

fun breathFirstTraversal(
        start: GraphVertex,
        filter: (vertex: GraphVertex) -> Boolean
): GraphVertex? {
    val queue: Queue<GraphVertex> = LinkedList<GraphVertex>()
    val visited = HashSet<GraphVertex>()

    queue.add(start)

    while (!queue.isEmpty()) {
        val vertex = queue.remove()

        if (visited.contains(vertex)) {
            continue
        }

        if (filter(vertex)) {
            return vertex
        }

        queue.addAll(vertex.getNeighbors())
        visited.add(vertex)
    }

    return null
}
