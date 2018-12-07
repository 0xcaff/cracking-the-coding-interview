package Chapter16_20

data class TrieLookupResult(val isPrefix: Boolean, val isValue: Boolean)

class TrieNode(
        private val children: MutableMap<Char, TrieNode> = mutableMapOf()
) {
    var isTerminal: Boolean = false
        private set

    val hasChildren: Boolean
        get() = children.isNotEmpty()

    fun add(string: String) {
        if (string.isEmpty()) {
            isTerminal = true
            return
        }

        children.getOrPut(string[0]) { TrieNode() }.add(string.slice(1..string.lastIndex))
    }

    fun check(prefix: String): TrieLookupResult? {
        if (prefix.isEmpty()) {
            return TrieLookupResult(isValue = isTerminal, isPrefix = hasChildren)
        }

        val firstCharChild = getNextRoot(prefix[0]) ?: return null
        return firstCharChild.check(prefix.slice(1..prefix.lastIndex))
    }

    fun getNextRoot(char: Char): TrieNode? {
        return children[char]
    }

    fun addAll(array: Array<String>) {
        for (el in array) {
            add(el)
        }
    }
}

