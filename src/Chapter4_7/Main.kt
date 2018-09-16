package Chapter4_7

import java.util.*

class Project(val name: String) {
    val dependencies: MutableList<Project> = mutableListOf()
    val dependents: MutableList<Project> = mutableListOf()

    fun addDependents(vararg projects: Project) {
        dependents.addAll(projects)
        projects.forEach { project -> project.dependencies.add(this) }
    }

    fun addDependencies(projects: List<Project>) {
        dependencies.addAll(projects)
        projects.forEach { project -> project.dependents.add(this) }
    }
}

class Graph(val vertices: List<Project>) {
    fun hasCycles(): Boolean = vertices.all { project -> !hasCycleStartingFrom(project) }

    companion object {
        fun hasCycleStartingFrom(start: Project): Boolean {
            val queue: Queue<Project> = LinkedList<Project>()
            val visited = HashSet<Project>()

            visited.add(start)
            queue.add(start)

            while (!queue.isEmpty()) {
                val vertex = queue.remove()

                if (visited.contains(vertex)) {
                    return true
                }

                queue.addAll(vertex.dependents)
                visited.add(vertex)
            }

            return false
        }
    }
}

fun main(args: Array<String>) = test()

fun test() {
    val a = Project("a")
    val b = Project("b")
    val c = Project("c")
    val d = Project("d")
    val e = Project("e")
    val f = Project("f")

    a.addDependents(d)
    b.addDependents(d)
    f.addDependents(a, b)
    d.addDependents(c)

    val vertices = listOf(a, b, c, d, e, f)
    val graph = Graph(vertices)

    val buildOrder = findBuildOrder(graph)

    require(buildOrder!! == listOf(f, a, b, d, c, e))
}

fun findBuildOrder(graph: Graph): List<Project>? {
    if (graph.hasCycles()) {
        return null
    }

    val root = Project("dummy")

    val rootDeps = graph.vertices.filter { project -> project.dependents.isEmpty() }
    root.addDependencies(rootDeps)

    val traversal = dependencyFirstTraversal(root)

    return traversal.slice(0 until traversal.size - 1)
}

fun dependencyFirstTraversal(
        root: Project,
        result: MutableList<Project> = mutableListOf()
): List<Project> {
    for (dep in root.dependencies) {
        dependencyFirstTraversal(dep, result)
    }

    if (result.contains(root)) {
        return result
    }

    result.add(root)
    return result
}
