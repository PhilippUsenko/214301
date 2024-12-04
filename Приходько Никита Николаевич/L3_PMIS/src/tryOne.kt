import java.util.Scanner

fun generateFunction(operation: String): ((Double, Double, Double) -> Double)? {
    return when (operation) {
        "max_of_three" -> { x, y, z ->
            var result = x
            if (y > result) result = y
            if (z > result) result = z
            result
        }
        "min_of_three" -> { x, y, z ->
            var result = x
            if (y < result) result = y
            if (z < result) result = z
            result
        }
        "average_of_three" -> { x, y, z -> (x + y + z) / 3 }
        "sum_of_three" -> { x, y, z -> x + y + z }
        else -> {
            println("Неизвестная операция. Попробуйте ещё раз.")
            null
        }
    }
}

fun applyFunction(func: (Double, Double, Double) -> Double, data: List<Triple<Double, Double, Double>>): List<Double> {
    val results = mutableListOf<Double>()
    for (triple in data) {
        val (x, y, z) = triple
        results.add(func(x, y, z))
    }
    return results
}

fun main() {
    val scanner = Scanner(System.`in`)

    println("Доступные операции: ")
    println("1. max_of_three - Нахождение максимума из трёх чисел")
    println("2. min_of_three - Нахождение минимума из трёх чисел")
    println("3. average_of_three - Нахождение среднего из трёх чисел")
    println("4. sum_of_three - Нахождение суммы трёх чисел")

    print("Введите операцию (например, max_of_three): ")
    val operation = scanner.nextLine().trim()
    val function = generateFunction(operation)

    if (function == null) return

    val data = mutableListOf<Triple<Double, Double, Double>>()
    println("\nВведите наборы чисел по три значения через пробел (например, '3 5 2').")
    println("Введите 'stop', чтобы завершить ввод данных.")

    while (true) {
        print("Введите числа: ")
        val line = scanner.nextLine().trim()
        if (line.equals("stop", ignoreCase = true)) break

        val numbers = line.split(" ").mapNotNull { it.toDoubleOrNull() }
        if (numbers.size != 3) {
            println("Ошибка: введите ровно три числа.")
            continue
        }
        data.add(Triple(numbers[0], numbers[1], numbers[2]))
    }

    if (data.isNotEmpty()) {
        val results = applyFunction(function, data)
        println("\nРезультаты обработки данных:")
        for (i in results.indices) {
            println("Набор ${i + 1}: результат = ${results[i]}")
        }
    } else {
        println("Нет данных для обработки.")
    }
}