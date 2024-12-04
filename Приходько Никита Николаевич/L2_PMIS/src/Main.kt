import java.util.Scanner

fun reverseAndInvert(arr: IntArray): IntArray {
    val reversedArray = arr.reversedArray()
    return reversedArray.map { -it }.toIntArray()
}

fun main() {
    val scanner = Scanner(System.`in`)
    println("Введите размер массива:")
    val size = scanner.nextInt()

    val numbers = IntArray(size)
    println("Введите элементы массива:")
    for (i in 0 until size) {
        numbers[i] = scanner.nextInt()
    }

    val result = reverseAndInvert(numbers)

    println("Реверсированный и инвертированный массив:")
    println(result.joinToString(", "))
}

