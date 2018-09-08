package Chapter1_9

fun main(args: Array<String>) {
    check(isRotationOf("waterbottle", "erbottlewat"))
    check(isRotationOf("waterbottle", "waterbottle"))
    check(!isRotationOf("waterbottle", "waterbott"))
    check(!isRotationOf("caress", "asana"))
}

fun isRotationOf(original: String, rotated: String): Boolean = isSubstring(rotated, original + original) && rotated.length == original.length

fun isSubstring(query: String, string: String): Boolean = string.indexOf(query) != -1
