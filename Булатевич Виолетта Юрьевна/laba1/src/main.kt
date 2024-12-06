import kotlin.math.pow

fun main() {
    var numbers: List<Double>? = null

    // Цикл, который будет продолжаться до тех пор, пока не введены корректные значения
    while (numbers == null || numbers.isEmpty()) {
        println("Введите числа через пробел:")

        // Чтение строки с числами
        val input = readLine()

        // Проверка, что строка не пустая и не null
        if (input.isNullOrBlank()) {
            println("Ошибка: строка пуста. Попробуйте снова.")
            continue // Переход на следующую итерацию цикла
        }

        // Преобразование строки в список чисел, проверка на корректность ввода
        numbers = input.split(" ").mapNotNull{ it.toDoubleOrNull() }

        // Проверка, что введено хотя бы одно корректное число
        if (numbers.isEmpty()) {
            println("Ошибка: не введено корректных чисел. Попробуйте снова.")
        }
    }

    // Вычисление среднего арифметического
    val arithmeticMean = numbers.sum() / numbers.size

    // Вычисление среднего геометрического
    val geometricMean = numbers.reduce{ acc, num -> acc * num }.pow(1.0 / numbers.size)

    // Вычисление среднего гармонического
    val harmonicMean = numbers.size / numbers.map{ 1 / it }.sum()

    // Вывод результатов
    println("Среднее арифметическое: $arithmeticMean")
    println("Среднее геометрическое: $geometricMean")
    println("Среднее гармоническое: $harmonicMean")
}
