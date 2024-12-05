import java.io.File

fun main() {
    println("Введите элементы массива через пробел:")
    val input = readLine() ?: ""
    val array = input.split(" ").mapNotNull { it.toIntOrNull() }.toIntArray()

    val permutations = mutableListOf<List<Int>>()
    generatePermutations(array.toList(), permutations)
    println("Все возможные перестановки:")
    permutations.forEach { println(it) }
    val outputFile = File("F:\\5 сем\\пмис\\lab2.txt")
    outputFile.printWriter().use { out ->
        permutations.forEach { out.println(it) }
    }
}

fun generatePermutations(array: List<Int>, result: MutableList<List<Int>>, start: Int = 0) {
    if (start >= array.size) {
        result.add(array)
        return
    }
    val seen = mutableSetOf<Int>()
    for (i in start until array.size) {
        if (seen.add(array[i])) {
            val newArray = array.toMutableList()
            newArray.swap(start, i)
            generatePermutations(newArray, result, start + 1)
        }
    }
}

fun MutableList<Int>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}