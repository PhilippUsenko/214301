fun areAnagrams(str1: String, str2: String): Boolean {
    fun cleanString(str: String): String {
        var result = ""
//        for (i in 0 until str.length) {
//            val char = str[i]
//            if ((char >= 'a' && char <= 'z') || (char >= 'A' && char <= 'Z') || (char >= '0' && char <= '9')) {
//                if (char >= 'A' && char <= 'Z') {
//                    result += (char + 32)
//                } else {
//                    result += char
//                }
//            }
//        }

        for (i in 0 until str.length) {
            val char = str[i]
            if (char.isLetter()) {
                result += char.lowercaseChar()
            }
        }

        return result
    }

    fun bubbleSort(str: String): String {
        val charArray = str.toCharArray()
        for (i in 0 until charArray.size) {
            for (j in 0 until charArray.size - 1 - i) {
                if (charArray[j] > charArray[j + 1]) {
                    val temp = charArray[j]
                    charArray[j] = charArray[j + 1]
                    charArray[j + 1] = temp
                }
            }
        }
        return String(charArray)
    }

    val cleanedStr1 = cleanString(str1)
    val cleanedStr2 = cleanString(str2)

    val sortedStr1 = bubbleSort(cleanedStr1)
    val sortedStr2 = bubbleSort(cleanedStr2)

    return sortedStr1 == sortedStr2
}

fun main() {
    println("Введите первую строку:")
    val input1 = readLine() ?: ""

    println("Введите вторую строку:")
    val input2 = readLine() ?: ""

    if (areAnagrams(input1, input2)) {
        println("Строки являются анаграммами.")
    } else {
        println("Строки не являются анаграммами.")
    }
}


















//fun areAnagrams(str1: String, str2: String): Boolean {
//    fun cleanString(str: String): String {
//        return str.filter { it.isLetterOrDigit() }.lowercase()
//    }
//
//
//    val cleanedStr1 = cleanString(str1)
//    val cleanedStr2 = cleanString(str2)
//
//    return cleanedStr1.toCharArray().sorted() == cleanedStr2.toCharArray().sorted()
//}
//
//fun main() {
//
//    println("Введите первую строку:")
//    val input1 = readLine() ?: ""
//
//    println("Введите вторую строку:")
//    val input2 = readLine() ?: ""
//
//
//    if (areAnagrams(input1, input2)) {
//        println("Строки являются анаграммами.")
//    } else {
//        println("Строки не являются анаграммами.")
//    }
//}