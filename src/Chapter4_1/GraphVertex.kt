package Chapter4_1

class GraphVertex {
    private val neighbors: MutableList<GraphVertex> = mutableListOf()

    constructor()

    constructor(neighbors: Array<GraphVertex>) {
        for (neighbor in neighbors) {
            this.neighbors.add(neighbor)
        }
    }

    fun addNeighbors(vararg graphVertex: GraphVertex)
        = this.neighbors.addAll(graphVertex)

    fun getNeighbors(): Array<GraphVertex>
        = this.neighbors.toTypedArray()
}
