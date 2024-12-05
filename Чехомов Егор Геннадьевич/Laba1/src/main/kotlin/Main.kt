fun areAnagrams(str1: String, str2: String): Boolean {
    val clean1 = str1.lowercase().filter { it.isLetter() }.toList().sorted()
    val clean2 = str2.lowercase().filter { it.isLetter() }.toList().sorted()
    return clean1 == clean2
}

fun main() {
    println("Введите первую строку:")
    val str1 = readLine() ?: ""
    println("Введите вторую строку:")
    val str2 = readLine() ?: " "
    if (areAnagrams(str1, str2)) {
        println("Строки являются анаграммами.")
    } else {
        println("Строки не являются анаграммами.")
    }

}